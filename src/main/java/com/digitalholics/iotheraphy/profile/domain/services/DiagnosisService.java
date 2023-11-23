package com.digitalholics.iotheraphy.profile.domain.services;

import com.digitalholics.iotheraphy.profile.domain.model.entities.Diagnosis;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosisService {

    Diagnosis getLast();
    List<Diagnosis> getByPatientId(Integer patientId);
    Diagnosis create(Diagnosis diagnosis);
    ResponseEntity<?> delete(Integer patientId);
}
