package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.dto.member.MemberRequestDTO;
import com.pdp.pdp_crm.entity.Member;
import com.pdp.pdp_crm.enums.CenterRole;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.mapper.MemberMapper;
import com.pdp.pdp_crm.repository.MemberRepository;
import com.pdp.pdp_crm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final CenterServiceImpl centerServiceImpl;

    @Override
    public MemberDTO create(MemberRequestDTO dto) {
        return memberMapper.toDto(
                memberRepository.save(
                        Member.builder()
                                .firstName(dto.getFirstName())
                                .lastName(dto.getLastName())
//                                .user()
                                .center(centerServiceImpl.findById(dto.getCenterId()))
                                .role(CenterRole.EMPLOYEE)
                                .entityStatus(EntityStatus.ACTIVE)
//                                .image(dto.getImageId())
                                .build()
                )
        );
    }

    @Override
    public MemberDTO findById(Long id) {
        return memberMapper.toDto(memberRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<Member> findByIdOptional(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public MemberDTO update(Long id, MemberRequestDTO dto) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setCenter(centerServiceImpl.findById(dto.getCenterId()));
        return memberMapper.toDto(memberRepository.save(member));
    }
}
