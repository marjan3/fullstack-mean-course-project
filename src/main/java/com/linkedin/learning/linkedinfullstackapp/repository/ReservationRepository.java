package com.linkedin.learning.linkedinfullstackapp.repository;

import com.linkedin.learning.linkedinfullstackapp.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository  extends CrudRepository<ReservationEntity, Long> {
}
