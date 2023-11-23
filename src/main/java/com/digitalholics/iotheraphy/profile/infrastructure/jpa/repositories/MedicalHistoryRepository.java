package com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories;

import com.digitalholics.iotheraphy.profile.domain.model.entities.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {

    MedicalHistory findByPatientId(Integer patientId);
}
