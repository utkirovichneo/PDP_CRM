package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.student.StudentRequestDTO;
import com.pdp.pdp_crm.entity.Student;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StudentService {

    StudentDTO save(Long centerId, StudentRequestDTO dto);

    StudentDTO getStudentById(Long centerId, Long id);

    Page<StudentDTO> findAll(Long centerId, PageableRequest pageableRequest);

    StudentDTO update(Long centerId, Long id, StudentRequestDTO dto);

    Boolean delete(Long centerId, Long id);

    Optional<Student> findById(Long centerId, Long studentId);

}