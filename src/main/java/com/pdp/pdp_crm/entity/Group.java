package com.pdp.pdp_crm.entity;

import com.pdp.pdp_crm.entity.base.BaseEntity;
import com.pdp.pdp_crm.enums.GroupDays;
import com.pdp.pdp_crm.enums.GroupStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Group extends BaseEntity {

    @Column(nullable = false)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Member teacher;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<GroupDays> days;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private GroupStatus status;
}