package com.digitalholics.iotheraphy.Appointment.domain.service;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.CheckRequest;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CheckRequestService {
    List<CheckRequest> getAll();
    Page<CheckRequest> getAll(Pageable pageable);
    CheckRequest getById(Integer checkRequestId);
    List<CheckRequest> getByPatientId(Integer patientId);

    //CheckRequest getByTherapistId(Integer therapistId);
    List<CheckRequest> getByStatus(Boolean status);

    CheckRequest create(CheckRequest checkRequest);

    CheckRequest update(Integer checkRequestId, CheckRequest request);
    ResponseEntity<?> delete(Integer checkRequestId);



}
