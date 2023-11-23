package com.digitalholics.iotheraphy.profile.domain.services;

import com.digitalholics.iotheraphy.profile.domain.model.entities.MedicalHistory;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.MedicalHistory.UpdateMedicalHistoryResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicalHistoryService {

    List<MedicalHistory> getAll();
    Page<MedicalHistory> getAll(Pageable pageable);
    MedicalHistory getById(Integer medicalHistoryId);
    MedicalHistory getByPatientId(Integer patientId);
    MedicalHistory create(CreateMedicalHistoryResource medicalHistory);
    MedicalHistory update(Integer medicalHistoryId, UpdateMedicalHistoryResource request);
    ResponseEntity<?> delete(Integer medicalHistoryId);
}
