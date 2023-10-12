package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Diagnosis;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Job;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.CreateJobResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.UpdateJobResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosisService {

    Diagnosis getLast();
    List<Diagnosis> getByPatientId(Integer patientId);
    Diagnosis create(Diagnosis diagnosis);
    ResponseEntity<?> delete(Integer patientId);
}
