package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.ImageRepository;
import com.pdp.pdp_crm.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

}
