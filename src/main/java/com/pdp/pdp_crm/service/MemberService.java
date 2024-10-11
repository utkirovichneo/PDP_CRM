package com.pdp.pdp_crm.service;


import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.dto.member.MemberRequestDTO;
import com.pdp.pdp_crm.entity.Member;

import java.util.Optional;

public interface MemberService {

    MemberDTO create(MemberRequestDTO dto);

    MemberDTO findById(Long id);
    Optional<Member> findByIdOptional(Long id);

    MemberDTO update(Long id, MemberRequestDTO dto);

}