package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.CollectionRepository;
import com.pdp.pdp_crm.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;

}
