package com.digitalholics.iotheraphy.profile.domain.services;

import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Patient;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.CreatePatientResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.UpdatePatientResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(Integer patientId);
    Patient getByDni(String dni);
    Patient getByUserId(Integer userId);

    Patient getLoggedInPatient();

    Patient create(CreatePatientResource patient);
    Patient update(Integer patientId, UpdatePatientResource request);
    ResponseEntity<?> delete(Integer patientId);

}
