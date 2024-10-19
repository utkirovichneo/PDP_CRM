package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.collection.CollectionDTO;
import com.pdp.pdp_crm.entity.Collection;

import java.util.List;
import java.util.Optional;

public interface CollectionService {

    List<Long> createCollection(Long centerId, Long groupId);

    Optional<Collection> findById(Long collectionId);

    Boolean schedulingCollection();

    CollectionDTO getCollection(Long centerId, Long studentId);

    Optional<Collection> findByIdAndCenterId(Long id, Long centerId);

}