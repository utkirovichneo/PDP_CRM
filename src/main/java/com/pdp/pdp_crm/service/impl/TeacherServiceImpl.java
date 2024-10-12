package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.teacher.TeacherDTO;
import com.pdp.pdp_crm.dto.teacher.TeacherRequestDTO;
import com.pdp.pdp_crm.mapper.AddressMapper;
import com.pdp.pdp_crm.repository.AddressRepository;
import com.pdp.pdp_crm.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public TeacherDTO create(TeacherRequestDTO dto) {
        return null;
    }

    @Override
    public TeacherDTO findById(Long id) {
        return null;
    }

    @Override
    public TeacherDTO update(Long id, TeacherRequestDTO dto) {
        return null;
    }
}
