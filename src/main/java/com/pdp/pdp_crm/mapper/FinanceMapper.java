package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.finance.FinanceDTO;
import com.pdp.pdp_crm.entity.Finance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinanceMapper extends EntityMapper<FinanceDTO, Finance> {
    @Override
    Finance toEntity(FinanceDTO dto);

    @Override
    FinanceDTO toDto(Finance entity);

    @Override
    List<Finance> toEntity(List<FinanceDTO> list);

    @Override
    List<FinanceDTO> toDto(List<Finance> list);
}
