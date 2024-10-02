package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.ImageRepository;
import com.pdp.pdp_crm.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
}
