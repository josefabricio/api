package com.digitalholics.iotheraphy.Consultation.domain.persistence;

import com.digitalholics.iotheraphy.Consultation.domain.model.entity.AvailableHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableHourRepository extends JpaRepository<AvailableHour, Integer> {

    List<AvailableHour> findByPhysiotherapistId(Integer physiotherapistId);
}
