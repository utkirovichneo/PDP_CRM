package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.invoice.InvoiceDTO;
import com.pdp.pdp_crm.entity.Invoice;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper extends EntityMapper<InvoiceDTO, Invoice> {
    @Override
    @Mapping(source = "collectionId", target = "collection.id")
    Invoice toEntity(InvoiceDTO dto);

    @Override
    @InheritInverseConfiguration
    InvoiceDTO toDto(Invoice entity);

    @Override
    List<Invoice> toEntity(List<InvoiceDTO> list);

    @Override
    List<InvoiceDTO> toDto(List<Invoice> list);
}
