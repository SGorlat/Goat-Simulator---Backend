package com.goat.repository;

import com.goat.model.CareerEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CareerEventLogRepository extends JpaRepository<CareerEventLog, Long> {

    List<CareerEventLog> findByCareerIdAndEventId(Long careerId, Long eventId);
}
