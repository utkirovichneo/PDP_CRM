package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.image.ImageDTO;
import com.pdp.pdp_crm.dto.image.ImageRequestDTO;

public interface ImageService {

    ImageDTO create(ImageRequestDTO dto);

    ImageDTO findById(Long id);

    ImageDTO update(Long id, ImageRequestDTO dto);

}