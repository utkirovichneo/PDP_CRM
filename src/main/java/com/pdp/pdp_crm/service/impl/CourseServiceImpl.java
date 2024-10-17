package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.dto.course.CourseRequestDTO;
import com.pdp.pdp_crm.entity.Course;
import com.pdp.pdp_crm.enums.CourseStatus;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.CourseMapper;
import com.pdp.pdp_crm.repository.CourseRepository;
import com.pdp.pdp_crm.service.CenterService;
import com.pdp.pdp_crm.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CenterService centerService;
    private final CourseMapper courseMapper;

    @Override
    public CourseDTO save(Long centerId, CourseRequestDTO dto) {

        var center = centerService.findById(centerId)
                .orElseThrow(() -> new NotFoundException("Center"));

        var course = courseRepository.save(Course.builder()
                .center(center)
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .countOfLessons(dto.getCountOfLessons())
                .entityStatus(EntityStatus.ACTIVE)
                .status(CourseStatus.ACTIVE)
                .build());

        return courseMapper.toDto(course);
    }

    @Override
    public CourseDTO findOne(Long centerId, Long id) {
        return courseMapper
                .toDto(courseRepository.findByIdAndCenterId(id, centerId)
                        .orElseThrow(() -> new NotFoundException("Course")));
    }

    @Override
    public Page<CourseDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(Arrays.asList(new SearchCriteria("center.id", "=", centerId)));
        }
        return courseRepository.findAll(
          new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(courseMapper::toDto);
    }

    @Override
    public CourseDTO update(Long centerId, Long id, CourseRequestDTO dto) {

        var course = courseRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Course"));

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setCountOfLessons(dto.getCountOfLessons());
        course.setEntityStatus(dto.getEntityStatus());
        course.setStatus(dto.getStatus());

        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public Boolean delete(Long centerId, Long id) {

        var course = courseRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Course"));

        course.setEntityStatus(EntityStatus.ARCHIVED);
        course.setStatus(CourseStatus.ARCHIVED);
        courseRepository.save(course);

        return Boolean.TRUE;
    }

    @Override
    public Optional<Course> findById(Long centerId, Long id) {
        return courseRepository.findByIdAndCenterId(id, centerId);
    }
}