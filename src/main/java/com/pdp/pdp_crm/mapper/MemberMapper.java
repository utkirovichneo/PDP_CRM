package com.pdp.pdp_crm.mapper;

import com.pdp.pdp_crm.dto.member.MemberDTO;
import com.pdp.pdp_crm.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {
    @Override
/*
    @Mapping(target = "image.url", ignore = true)
*/
    @Mapping(target = "user.id", ignore = true)
    @Mapping(target = "center.id", ignore = true)
    Member toEntity(MemberDTO dto);

    @Override
/*
    @Mapping(target = "imageUrl", source = "image.url")
*/
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "centerId", source = "center.id")
    MemberDTO toDto(Member entity);

    @Override
    List<Member> toEntity(List<MemberDTO> list);

    @Override
    List<MemberDTO> toDto(List<Member> list);
}
