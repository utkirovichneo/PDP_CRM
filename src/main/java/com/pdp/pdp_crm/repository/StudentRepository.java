package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Student;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends BaseRepository<Student, Long> {

    Optional<Student> findByIdAndGroupCenterId(Long id, Long centerId);

}