package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.student.StudentRequestDTO;
import com.pdp.pdp_crm.entity.Address;
import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.entity.Student;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.mapper.StudentMapper;
import com.pdp.pdp_crm.repository.StudentRepository;
import com.pdp.pdp_crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupServiceImpl groupServiceImpl;


    @Override
    public StudentDTO create(StudentRequestDTO dto) {
        return studentMapper.toDto(
                studentRepository.save(
                        Student.builder()
                                .phoneNumber(dto.getPhoneNumber())
                                .firstName(dto.getFirstName())
                                .lastName(dto.getLastName())
                                .dateOfBirth(dto.getDateOfBirth())
                                .gender(dto.getGender())
                                .status(dto.getStatus())
                                .entityStatus(EntityStatus.ACTIVE)
                                .group(groupServiceImpl.findByIdOptional(dto.getGroupId()).orElseThrow(RuntimeException::new))
                                .build()

                )
        );
    }

    @Override
    public StudentDTO findById(Long id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public StudentDTO update(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setStatus(dto.getStatus());
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public Optional<Student> findByIdOptional(Long id) {
        return Optional.empty();
    }
}
