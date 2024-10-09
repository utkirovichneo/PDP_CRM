package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.payment.PaymentDTO;
import com.pdp.pdp_crm.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
    @Override
    @Mapping(target = "collection", ignore = true)
    Payment toEntity(PaymentDTO dto);

    @Override
    @Mapping(source = "collection.id", target = "collectionId")
    PaymentDTO toDto(Payment entity);

    @Override
    List<Payment> toEntity(List<PaymentDTO> list);

    @Override
    List<PaymentDTO> toDto(List<Payment> list);
}
