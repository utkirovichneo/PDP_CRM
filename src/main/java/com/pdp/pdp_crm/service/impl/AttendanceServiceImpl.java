package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.attendance.AttendanceDTO;
import com.pdp.pdp_crm.entity.Attendance;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.AttendanceMapper;
import com.pdp.pdp_crm.repository.AttendanceRepository;
import com.pdp.pdp_crm.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceDTO save(Attendance attendance){
        return attendanceMapper.toDto(attendanceRepository.save(attendance));
    }

    @Override
    public Page<AttendanceDTO> filter(Long teacherId, Long groupId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("group.id", "=", groupId));
            pageableRequest.getSearch().add(new SearchCriteria("group.teacher.id", "=", teacherId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("group.id", "=", groupId)));
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("group.teacher.id", "=", teacherId)));
        }
        return attendanceRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(attendanceMapper::toDto);
    }
}