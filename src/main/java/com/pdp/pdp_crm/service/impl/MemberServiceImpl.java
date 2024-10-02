package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.repository.MemberRepository;
import com.pdp.pdp_crm.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
