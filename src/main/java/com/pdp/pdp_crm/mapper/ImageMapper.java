package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.image.ImageDTO;
import com.pdp.pdp_crm.entity.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<ImageDTO, Image> {
    @Override
    Image toEntity(ImageDTO dto);

    @Override
    ImageDTO toDto(Image entity);

    @Override
    List<Image> toEntity(List<ImageDTO> list);

    @Override
    List<ImageDTO> toDto(List<Image> list);
}
