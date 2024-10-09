package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableDTO;
import com.pdp.pdp_crm.entity.LessonAvailable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonAvailableMapper extends EntityMapper<LessonAvailableDTO, LessonAvailable> {
    @Override
    @Mapping(target = "group", ignore = true)
    LessonAvailable toEntity(LessonAvailableDTO dto);

    @Override
    @Mapping(source = "group.id", target = "groupId")
    LessonAvailableDTO toDto(LessonAvailable entity);

    @Override
    List<LessonAvailable> toEntity(List<LessonAvailableDTO> list);

    @Override
    List<LessonAvailableDTO> toDto(List<LessonAvailable> list);
}
