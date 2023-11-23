package com.digitalholics.iotheraphy.consultation.infrastructure.persistence.jpa.repositories;

import com.digitalholics.iotheraphy.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    Consultation findByTopic(String topic);

    Consultation findConsultationByPhysiotherapistId(Integer physiotherapistId);

    List<Consultation> findByPatientId(Integer patientId);

    List<Consultation> findByPhysiotherapistId(Integer physiotherapistId);


}
