package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.MemberRepository;
import com.pdp.pdp_crm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

}
