package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.MedicalHistory;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory.UpdateMedicalHistoryResource;
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
