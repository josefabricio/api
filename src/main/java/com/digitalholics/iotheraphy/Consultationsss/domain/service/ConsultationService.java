package com.digitalholics.iotheraphy.Consultationsss.domain.service;

import com.digitalholics.iotheraphy.Consultationsss.domain.model.entity.Consultation;
import com.digitalholics.iotheraphy.Consultationsss.resource.CreateConsultationResource;
import com.digitalholics.iotheraphy.Consultationsss.resource.UpdateConsultationResource;
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
