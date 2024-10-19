package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.collection.CollectionDTO;
import com.pdp.pdp_crm.dto.payment.PaymentDTO;
import com.pdp.pdp_crm.dto.payment.PaymentRequestDTO;
import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.student.StudentRequestDTO;
import com.pdp.pdp_crm.entity.Student;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.enums.StudentStatus;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.StudentMapper;
import com.pdp.pdp_crm.repository.StudentRepository;
import com.pdp.pdp_crm.service.CollectionService;
import com.pdp.pdp_crm.service.GroupService;
import com.pdp.pdp_crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CollectionService collectionService;
    private final PaymentServiceImpl paymentServiceImpl;

    @Autowired
    @Lazy
    public void setStudentServiceImpl(GroupService groupService){
        this.groupService = groupService;
    }
    private GroupService groupService;


    @Override
    public StudentDTO save(Long centerId, StudentRequestDTO dto) {

        var student = studentRepository.save(Student.builder()
                .phoneNumber(dto.getPhoneNumber())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .status(StudentStatus.ACTIVE)
                .entityStatus(EntityStatus.ACTIVE)
                .group(groupService.findById(centerId, dto.getGroupId())
                        .orElseThrow(()-> new NotFoundException("Group")))
                .build());

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO getStudentById(Long centerId, Long id) {
        return studentMapper.toDto(
                studentRepository
                        .findByIdAndGroupCenterId(id, centerId)
                        .orElseThrow(() -> new NotFoundException("Student")));
    }

    @Override
    public Page<StudentDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("group.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(new ArrayList<>(List.of(new SearchCriteria("group.center.id", "=", centerId))));
        }
        return studentRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(studentMapper::toDto);
    }

    @Override
    public StudentDTO update(Long centerId, Long id, StudentRequestDTO dto) {

        var student = studentRepository
                .findByIdAndGroupCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Student"));
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setStatus(StudentStatus.ACTIVE);
        student.setEntityStatus(EntityStatus.ACTIVE);
        student.setGroup(groupService.findById(centerId, dto.getGroupId()).orElseThrow(()-> new NotFoundException("Group")));
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public Boolean delete(Long centerId, Long id) {
        var student = studentRepository.findByIdAndGroupCenterId(id, centerId)
                .orElseThrow(()-> new NotFoundException("Student"));
        student.setEntityStatus(EntityStatus.ARCHIVED);
        student.setStatus(StudentStatus.ARCHIVED);
        studentRepository.save(student);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Student> findById(Long centerId, Long studentId) {
        return studentRepository.findByIdAndGroupCenterId(studentId, centerId);
    }

    @Override
    public List<StudentDTO> findAllStudents(Long centerId, Long groupId) {
        return studentRepository
                .findAllByGroupCenterIdAndGroupId(centerId, groupId)
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findAllStudent(Long centerId, Long groupId) {
        return studentRepository.findAllByGroupCenterIdAndGroupIdAndEntityStatusEquals(centerId, groupId, EntityStatus.ACTIVE);
    }

    @Override
    public CollectionDTO getCollection(Long centerId, Long studentId) {
        CollectionDTO collection = collectionService.getCollection(centerId, studentId);
        if(collection == null){
            throw new NotFoundException("Collection");
        }
            return collection;
    }

    @Override
    public Page<PaymentDTO> findAllPayments(Long centerId, PageableRequest pageableRequest) {
        return paymentServiceImpl.findAll(centerId, pageableRequest);
    }

    @Override
    public PaymentDTO payment(Long centerId, PaymentRequestDTO dto) {
        return paymentServiceImpl.payment(centerId, dto);
    }
}