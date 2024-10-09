package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.entity.FinanceIncome;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinanceIncomeMapper extends EntityMapper<FinanceIncomeDTO, FinanceIncome> {
    @Override
    FinanceIncome toEntity(FinanceIncomeDTO dto);

    @Override
    FinanceIncomeDTO toDto(FinanceIncome entity);

    @Override
    List<FinanceIncome> toEntity(List<FinanceIncomeDTO> list);

    @Override
    List<FinanceIncomeDTO> toDto(List<FinanceIncome> list);
}
