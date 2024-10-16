package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.dto.member.MemberRequestDTO;
import com.pdp.pdp_crm.entity.Member;
import com.pdp.pdp_crm.entity.Role;
import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.enums.CenterRole;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.MemberMapper;
import com.pdp.pdp_crm.repository.MemberRepository;
import com.pdp.pdp_crm.repository.RoleRepository;
import com.pdp.pdp_crm.repository.UserRepository;
import com.pdp.pdp_crm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CenterServiceImpl centerServiceImpl;

    @Override
    public MemberDTO save(Long centerId, MemberRequestDTO dto) {

        Role role = roleRepository.findById(
                dto.getRole().equals(CenterRole.TEACHER) ? 2L : (
                        dto.getRole().equals(CenterRole.MANAGER)?4L:1L))
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = userRepository.save(
                User.builder()
                        .phoneNumber(dto.getPhoneNumber())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .roles(Set.of(role))
                        .build());

        var member = memberRepository.save(
                Member.builder()
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .user(user)
                        .center(centerServiceImpl.findById(dto.getCenterId()).orElseThrow(()->new RuntimeException("Center not found")))
                        .role(dto.getRole())
                        .entityStatus(EntityStatus.ACTIVE)
                        .build()
        );

        return memberMapper.toDto(member);
    }

    @Override
    public MemberDTO update(Long centerId, Long id, MemberRequestDTO dto) {

        var member = memberRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setRole(dto.getRole());



        return memberMapper.toDto(memberRepository.save(member));
    }

    @Override
    public MemberDTO getById(Long centerId, Long memberId) {
        return memberMapper.toDto(
                memberRepository
                .findByIdAndCenterId(memberId, centerId)
                .orElseThrow(() -> new RuntimeException("Member not found")));
    }

    @Override
    public Page<MemberDTO> getAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("center.id", "=", centerId)));
        }
        return memberRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(memberMapper::toDto);
    }

    @Override
    public Boolean delete(Long centerId, Long memberId) {

        var member = memberRepository.findByIdAndCenterId(memberId, centerId).orElseThrow(()->new RuntimeException("Member not found"));
        member.setEntityStatus(EntityStatus.ARCHIVED);
        memberRepository.save(member);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Member> findById(Long centerId, Long memberId) {
        return memberRepository.findByIdAndCenterId(memberId, centerId);
    }
}