package com.pdp.pdp_crm.repository;

import com.pdp.pdp_crm.entity.Room;
import com.pdp.pdp_crm.repository.baserepository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends BaseRepository<Room, Long> {
}