package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.StudentRepository;
import com.pdp.pdp_crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

}
