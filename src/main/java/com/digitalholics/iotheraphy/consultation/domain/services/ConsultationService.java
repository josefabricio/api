package com.digitalholics.iotheraphy.consultation.domain.services;

import com.digitalholics.iotheraphy.consultation.domain.model.aggregates.Consultation;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.CreateConsultationResource;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.UpdateConsultationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsultationService {

    List<Consultation> getAll();
    Page<Consultation> getAll(Pageable pageable);
    Consultation getById(Integer consultationId);
    List<Consultation> getByPatientId(Integer patientId);
    List<Consultation> getByPhysiotherapistId(Integer physiotherapistId);

    Consultation getConsultationByPhysiotherapistId(Integer physiotherapistId);

    Consultation create(CreateConsultationResource consultation);
    Consultation update(Integer consultationId, UpdateConsultationResource request);
    ResponseEntity<?> delete(Integer ConsultationId);

    Consultation updateDiagnosis(Integer consultationId, String diagnosis);

}
