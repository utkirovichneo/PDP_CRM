package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.teacher.TeacherDTO;
import com.pdp.pdp_crm.dto.teacher.TeacherRequestDTO;

import java.util.Optional;

public interface TeacherService {

    TeacherDTO create(TeacherRequestDTO dto);

    TeacherDTO findById(Long id);

    TeacherDTO update(Long id, TeacherRequestDTO dto);

}
