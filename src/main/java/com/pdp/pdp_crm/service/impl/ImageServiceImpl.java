package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.image.ImageDTO;
import com.pdp.pdp_crm.dto.image.ImageRequestDTO;
import com.pdp.pdp_crm.entity.Image;
import com.pdp.pdp_crm.mapper.ImageMapper;
import com.pdp.pdp_crm.repository.ImageRepository;
import com.pdp.pdp_crm.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public ImageDTO create(ImageRequestDTO dto) {
        return imageMapper.toDto(
                imageRepository.save(
                        Image.builder()
                                .name(dto.getImage().getName())
//                                .prefix(dto.getImage().get)
//                                .url()
                                .build()

                )
        );
    }

    @Override
    public ImageDTO findById(Long id) {
        return imageMapper.toDto(imageRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public ImageDTO update(Long id, ImageRequestDTO dto) {
        Image image = imageRepository.findById(id).orElseThrow(RuntimeException::new);
        image.setName(dto.getImage().getName());
        return imageMapper.toDto(imageRepository.save(image));
    }
}
