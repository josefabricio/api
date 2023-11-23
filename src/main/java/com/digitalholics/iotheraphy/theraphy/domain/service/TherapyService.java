package com.digitalholics.iotheraphy.theraphy.domain.service;

import com.digitalholics.iotheraphy.theraphy.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.theraphy.resource.Therapy.CreateTherapyResource;
import com.digitalholics.iotheraphy.theraphy.resource.Therapy.UpdateTherapyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TherapyService {

    List<Therapy> getAll();

    Page<Therapy> getAll(Pageable pageable);
    List<Therapy> getTherapyByPatientId(Integer patientId);

    Therapy getActiveTherapyByPatientId();

    Therapy getTherapyByPhysiotherapistIdAndPatientId(Integer physiotherapistId, Integer patientId);

    Therapy getById(Integer therapyId);

    Therapy create(CreateTherapyResource therapy);
    Therapy update(Integer therapyId, UpdateTherapyResource request);

    ResponseEntity<?> delete(Integer therapyId);

}
