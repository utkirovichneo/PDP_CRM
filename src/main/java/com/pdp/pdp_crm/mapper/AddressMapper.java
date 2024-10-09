package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.entity.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
    @Override
    Address toEntity(AddressDTO dto);

    @Override
    AddressDTO toDto(Address entity);

    @Override
    List<Address> toEntity(List<AddressDTO> list);

    @Override
    List<AddressDTO> toDto(List<Address> list);
}
