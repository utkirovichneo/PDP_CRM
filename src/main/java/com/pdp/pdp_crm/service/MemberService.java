package com.pdp.pdp_crm.service;

import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.dto.member.MemberRequestDTO;
import com.pdp.pdp_crm.entity.Member;
import com.pdp.pdp_crm.filter.PageableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberService {

    MemberDTO save(Long centerId, MemberRequestDTO dto);

    MemberDTO getById(Long centerId, Long memberId);

    Page<MemberDTO> getAll(Long centerId, PageableRequest pageableRequest);

    Boolean delete(Long centerId, Long memberId);

    Optional<Member> findById(Long centerId, Long memberId);

    MemberDTO update(Long centerId, Long id, MemberRequestDTO dto);

}