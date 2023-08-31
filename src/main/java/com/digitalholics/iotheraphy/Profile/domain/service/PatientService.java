package com.digitalholics.iotheraphy.Profile.domain.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(Integer patientId);
    Patient getByDni(String dni);
    Patient getByUserId(Integer userId);
    Patient create(Patient patient);
    Patient update(Integer patientId, Patient request);
    ResponseEntity<?> delete(Integer patientId);
}
