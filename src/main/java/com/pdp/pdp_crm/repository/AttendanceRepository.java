package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Attendance;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends BaseRepository<Attendance, Long> {
}