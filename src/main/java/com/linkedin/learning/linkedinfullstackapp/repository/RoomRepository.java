package com.linkedin.learning.linkedinfullstackapp.repository;

import com.linkedin.learning.linkedinfullstackapp.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    RoomEntity findById(Long id);
}

