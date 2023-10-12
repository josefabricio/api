package com.digitalholics.iotheraphy.Consultation.domain.service;

import com.digitalholics.iotheraphy.Consultation.domain.model.entity.Consultation;
import com.digitalholics.iotheraphy.Consultation.resource.CreateConsultationResource;
import com.digitalholics.iotheraphy.Consultation.resource.UpdateConsultationResource;
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

    Consultation create(CreateConsultationResource consultation);
    Consultation update(Integer consultationId, UpdateConsultationResource request);
    ResponseEntity<?> delete(Integer ConsultationId);

    Consultation updateDiagnosis(Integer consultationId, String diagnosis);

}
