package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends BaseRepository<Center, Long> {
    @Query("from Center c where c.user.id = :userId")
    Center findByOwnerId(Long userId);
}