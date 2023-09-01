package com.digitalholics.iotheraphy.Theraphy.domain.persistence;

import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheraphyRepository extends JpaRepository<Theraphy, Integer> {

    List<Theraphy> findTheraphiesByPatientId(Integer patientId);
    List<Theraphy> findTheraphiesByTheraphistId(Integer physiotherapistId);
}
