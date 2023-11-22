package com.digitalholics.iotheraphy.consultation.infrastructure.persistence.jpa.repositories;

import com.digitalholics.iotheraphy.consultation.domain.model.entities.AvailableHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableHourRepository extends JpaRepository<AvailableHour, Integer> {

    List<AvailableHour> findByPhysiotherapistId(Integer physiotherapistId);



}
