package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Collection;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends BaseRepository<Collection, Long> {

    List<Collection> findAllByStudentId(Long studentId);

}