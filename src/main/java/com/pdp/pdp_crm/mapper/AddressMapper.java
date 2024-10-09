package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.address.AddressDTO;
import com.pdp.pdp_crm.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address>{
}
