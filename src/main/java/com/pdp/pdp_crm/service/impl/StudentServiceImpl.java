package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.student.StudentRequestDTO;
import com.pdp.pdp_crm.entity.Student;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.StudentMapper;
import com.pdp.pdp_crm.repository.StudentRepository;
import com.pdp.pdp_crm.service.GroupService;
import com.pdp.pdp_crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupService groupService;


    @Override
    public StudentDTO save(Long centerId, StudentRequestDTO dto) {

        var student = studentRepository.save(Student.builder()
                .phoneNumber(dto.getPhoneNumber())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .status(dto.getStatus())
                .entityStatus(EntityStatus.ACTIVE)
                .group(groupService.findById(centerId, dto.getGroupId())
                        .orElseThrow(()-> new RuntimeException("Group not found")))
                .build());

        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO getStudentById(Long centerId, Long id) {
        return studentMapper.toDto(
                studentRepository
                        .findByIdAndGroupCenterId(id, centerId)
                        .orElseThrow(() -> new RuntimeException("Student not found")));
    }

    @Override
    public Page<StudentDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("group.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(new ArrayList<>(Arrays.asList(new SearchCriteria("group.center.id", "=", centerId))));
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
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setStatus(dto.getStatus());
        student.setEntityStatus(EntityStatus.ACTIVE);
        student.setGroup(groupService.findById(centerId, dto.getGroupId()).orElseThrow(()-> new RuntimeException("Group not found")));
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public Boolean delete(Long centerId, Long id) {
        var student = studentRepository.findByIdAndGroupCenterId(id, centerId)
                .orElseThrow(()-> new RuntimeException("Student not found"));
        student.setEntityStatus(EntityStatus.ARCHIVED);
        studentRepository.save(student);
        return Boolean.TRUE;
    }
}