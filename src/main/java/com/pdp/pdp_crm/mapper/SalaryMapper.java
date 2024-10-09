package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.salary.SalaryDTO;
import com.pdp.pdp_crm.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaryMapper extends EntityMapper<SalaryDTO, Salary> {
    @Override
    @Mapping(target = "member", ignore = true)
    Salary toEntity(SalaryDTO dto);

    @Override
    @Mapping(source = "member.id", target = "memberId")
    SalaryDTO toDto(Salary entity);

    @Override
    List<Salary> toEntity(List<SalaryDTO> list);

    @Override
    List<SalaryDTO> toDto(List<Salary> list);
}
