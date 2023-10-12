package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Diagnosis;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosisService {

    Diagnosis getLast();
    List<Diagnosis> getByPatientId(Integer patientId);
    Diagnosis create(Diagnosis diagnosis);
    ResponseEntity<?> delete(Integer patientId);
}
