package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.persistence;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    List<Diagnosis> findByPatientId(Integer patientId);


}
