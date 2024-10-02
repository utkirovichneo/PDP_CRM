package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.StudentRepository;
import com.pdp.pdp_crm.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
