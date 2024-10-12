package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.course.CourseDTO;
import com.pdp.pdp_crm.dto.course.CourseRequestDTO;
import com.pdp.pdp_crm.entity.Course;
import com.pdp.pdp_crm.enums.CourseStatus;
import com.pdp.pdp_crm.mapper.CourseMapper;
import com.pdp.pdp_crm.repository.CourseRepository;
import com.pdp.pdp_crm.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CenterServiceImpl centerServiceImpl;

    @Override
    public CourseDTO create(CourseRequestDTO dto) {
        return courseMapper.toDto(
                courseRepository.save(
                        Course.builder()
                                .center(centerServiceImpl.findById(dto.getCenterId()))
                                .name(dto.getName())
                                .description(dto.getDescription())
                                .price(dto.getPrice())
                                .duration(dto.getDuration())
                                .countOfLessons(dto.getCountOfLessons())
                                .status(CourseStatus.ACTIVE)
                                .build()

                )
        );
    }

    @Override
    public CourseDTO findById(Long id) {
        return courseMapper.toDto(courseRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public CourseDTO update(Long id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id).orElseThrow(RuntimeException::new);
        course.setCenter(centerServiceImpl.findById(dto.getCenterId()));
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setCountOfLessons(dto.getCountOfLessons());
        course.setStatus(CourseStatus.ACTIVE);
        return courseMapper.toDto(courseRepository.save(course));
    }
}
