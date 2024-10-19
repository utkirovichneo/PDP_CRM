package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.enums.GroupStatus;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends BaseRepository<Group, Long> {

    Optional<Group> findByIdAndCenterId(Long id, Long centerId);

    Optional<Group> findByIdAndTeacherId(Long id, Long teacherId);

    List<Group> findALlByStatusEquals(GroupStatus status);

}