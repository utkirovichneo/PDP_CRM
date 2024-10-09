package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.financeoutcome.FinanceOutcomeDTO;
import com.pdp.pdp_crm.entity.FinanceOutcome;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinanceOutcomeMapper extends EntityMapper<FinanceOutcomeDTO, FinanceOutcome> {
    @Override
    FinanceOutcome toEntity(FinanceOutcomeDTO dto);

    @Override
    FinanceOutcomeDTO toDto(FinanceOutcome entity);

    @Override
    List<FinanceOutcome> toEntity(List<FinanceOutcomeDTO> list);

    @Override
    List<FinanceOutcomeDTO> toDto(List<FinanceOutcome> list);
}
