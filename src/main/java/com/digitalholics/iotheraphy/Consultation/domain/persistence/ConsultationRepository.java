package com.digitalholics.iotheraphy.Consultation.domain.persistence;

import com.digitalholics.iotheraphy.Consultation.domain.model.entity.Consultation;
import com.digitalholics.iotheraphy.Consultation.resource.UpdateConsultationResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    Consultation findByTopic(String topic);

    List<Consultation> findByPatientId(Integer patientId);

    List<Consultation> findByPhysiotherapistId(Integer physiotherapistId);


}
