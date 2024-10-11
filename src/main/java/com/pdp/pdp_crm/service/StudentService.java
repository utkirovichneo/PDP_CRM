package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.student.StudentDTO;
import com.pdp.pdp_crm.dto.student.StudentRequestDTO;
import com.pdp.pdp_crm.entity.Student;

import java.util.Optional;

public interface StudentService {

    StudentDTO create(StudentRequestDTO dto);

    StudentDTO findById(Long id);

    StudentDTO update(Long id, StudentRequestDTO dto);
    Optional<Student> findByIdOptional(Long id);

}