package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.entity.Center;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AddressMapper.class})
public interface CenterMapper extends EntityMapper<CenterDTO, Center> {
    @Override
    @Mapping(target = "logo", ignore = true)
    Center toEntity(CenterDTO dto);

    @Override
    @Mapping(source = "logo.url", target = "logo")
    CenterDTO toDto(Center entity);

    @Override
    List<Center> toEntity(List<CenterDTO> list);

    @Override
    List<CenterDTO> toDto(List<Center> list);
}
