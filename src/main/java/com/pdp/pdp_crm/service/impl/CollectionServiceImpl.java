package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.CollectionRepository;
import com.pdp.pdp_crm.service.CollectionService;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }
}
