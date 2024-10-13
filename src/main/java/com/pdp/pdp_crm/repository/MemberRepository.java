package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Member;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends BaseRepository<Member, Long> {

    Optional<Member> findByIdAndCenterId(Long id, Long centerId);

}