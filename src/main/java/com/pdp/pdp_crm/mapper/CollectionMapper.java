package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.collection.CollectionDTO;
import com.pdp.pdp_crm.entity.Collection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface CollectionMapper extends EntityMapper<CollectionDTO, Collection> {
    @Override
    Collection toEntity(CollectionDTO dto);

    @Override
    CollectionDTO toDto(Collection entity);

    @Override
    List<Collection> toEntity(List<CollectionDTO> list);

    @Override
    List<CollectionDTO> toDto(List<Collection> list);
}
