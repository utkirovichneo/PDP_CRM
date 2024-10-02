package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Image;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends BaseRepository<Image, Long> {
}