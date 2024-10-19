package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.group.GroupDTO;
import com.pdp.pdp_crm.dto.group.GroupRequestDTO;
import com.pdp.pdp_crm.dto.group.GroupResDTO;
import com.pdp.pdp_crm.dto.lessonavailable.LessonAvailableRequestDTO;
import com.pdp.pdp_crm.dto.teacher.TeacherDTO;
import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.entity.Member;
import com.pdp.pdp_crm.enums.EntityStatus;
import com.pdp.pdp_crm.enums.GroupDays;
import com.pdp.pdp_crm.enums.GroupStatus;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.*;
import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CenterService centerServiceImpl;
    private final CourseService courseServiceImpl;
    private final MemberService memberServiceImpl;
    private final RoomService roomServiceImpl;
    private final LessonAvailableService lessonAvailableServiceImpl;
    private final CenterMapper centerMapper;
    private final CourseMapper courseMapper;
    private final RoomMapper roomMapper;
    @Autowired
    @Lazy
    public void setGroupServiceImpl(StudentService studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }
    private StudentService studentServiceImpl;

    @Override
    public GroupDTO findOne(Long centerId, Long id) {
        return groupMapper.toDto(groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Group")));
    }

    @Override
    public GroupResDTO findGroupResDTO(Long centerId, Long id) {

        var group = groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        GroupResDTO groupResDTO = new GroupResDTO();

        groupResDTO.setId(group.getId());
        groupResDTO.setGroupName(group.getGroupName());
        groupResDTO.setCenter(centerMapper.toDto(group.getCenter()));
        groupResDTO.setCourse(courseMapper.toDto(group.getCourse()));

        Member teacher = group.getTeacher();
        if(teacher != null) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setUserId(teacher.getUser().getId());
            teacherDTO.setCenterId(centerId);
            teacherDTO.setFirstName(teacher.getFirstName());
            teacherDTO.setLastName(teacher.getLastName());
            teacherDTO.setRole(teacher.getRole());
            teacherDTO.setEntityStatus(teacher.getEntityStatus());

            groupResDTO.setTeacher(teacherDTO);
        }

        groupResDTO.setRoom(roomMapper.toDto(group.getRoom()));
        groupResDTO.setDays(group.getDays());
        groupResDTO.setStartTime(group.getStartTime());
        groupResDTO.setEndTime(group.getEndTime());
        groupResDTO.setStatus(group.getStatus());
        groupResDTO.setEntityStatus(group.getEntityStatus());
        groupResDTO.setCurrentStage(group.getCurrentStage());

        //! AttendanceDTO ni set qilish kerak

        groupResDTO.setStudents(studentServiceImpl.findAllStudents(centerId, group.getId()));

        return groupResDTO;
    }

    @Override
    public GroupDTO save(Long centerId, GroupRequestDTO dto) {

        var group = groupRepository.save(Group.builder()
                .groupName(dto.getGroupName())
                .center(centerServiceImpl.findById(centerId).orElseThrow(() -> new NotFoundException("Center")))
                .course(courseServiceImpl.findById(centerId, dto.getCourseId()).orElseThrow(() -> new NotFoundException("Course")))
                .teacher(memberServiceImpl.findById(centerId, dto.getTeacherId()).orElseThrow(() -> new NotFoundException("Teacher")))
                .room(roomServiceImpl.findById(centerId, dto.getRoomId()).orElseThrow(() -> new NotFoundException("Room")))
                .days(dto.getDays())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(dto.getStatus())
                .entityStatus(dto.getEntityStatus())
                .build());

        return groupMapper.toDto(group);
    }

    @Override
    public GroupDTO update(Long centerId, Long id, GroupRequestDTO dto) {
        var group = groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Group"));

        group.setCourse(courseServiceImpl.findById(id, dto.getCourseId()).orElseThrow(() -> new NotFoundException("Course")));
        group.setTeacher(memberServiceImpl.findById(id, dto.getTeacherId()).orElseThrow(() -> new NotFoundException("Teacher")));
        group.setRoom(roomServiceImpl.findById(id, dto.getRoomId()).orElseThrow(() -> new NotFoundException("Room")));
        group.setDays(dto.getDays());
        group.setStartTime(dto.getStartTime());
        group.setEndTime(dto.getEndTime());
        group.setStatus(dto.getStatus());
        group.setEntityStatus(dto.getEntityStatus());
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public Page<GroupDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(List.of(new SearchCriteria("center.id", "=", centerId)));
        }
        return groupRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(groupMapper::toDto);
    }


    @Override
    public Optional<Group> findById(Long centerId, Long id) {
        return groupRepository.findByIdAndCenterId(id, centerId);
    }

    @Override
    public Optional<Group> findByTeacherId(Long teacherId, Long id) {
        return groupRepository.findByIdAndTeacherId(id, teacherId);
    }

    @Override
    public Boolean delete(Long centerId, Long id) {
        var group = groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Group"));

        group.setStatus(GroupStatus.INACTIVE);
        group.setEntityStatus(EntityStatus.ARCHIVED);
        groupRepository.save(group);
        return Boolean.TRUE;
    }

    @Override
    public Boolean start(Long centerId, Long id) {

        var group = groupRepository.findByIdAndCenterId(id, centerId)
                .orElseThrow(() -> new NotFoundException("Group"));
        var course = group.getCourse();
            var days = group.getDays();
                long countOfLessons = course.getCountOfLessons();
                    long counter = 0;
                        var currentDate = LocalDate.now();
            while (counter < countOfLessons){
                var currentDateDayOfWeek = currentDate.getDayOfWeek();

            for (GroupDays day : days) {
                if(currentDateDayOfWeek.equals(day.getDayOfWeek())){

                    var lesson = new LessonAvailableRequestDTO();
                    lesson.setGroupId(group.getId());
                    lesson.setIsLessonAvailable(false);
                    lesson.setDate(currentDate);

                    lessonAvailableServiceImpl.save(group.getTeacher().getId(), lesson);

                    counter++;

                    if(counter >= countOfLessons){
                        break;
                    }
                }
            }
                        currentDate = currentDate.plusDays(1);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findALlByStatusEquals(GroupStatus.ACTIVE);
    }
}