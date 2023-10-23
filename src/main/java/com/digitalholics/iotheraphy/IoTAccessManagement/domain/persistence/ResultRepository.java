package com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.Result;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result findByTreatmentId(Integer treatmentId);
}
