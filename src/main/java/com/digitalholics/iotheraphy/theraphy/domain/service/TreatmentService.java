package com.digitalholics.iotheraphy.theraphy.domain.service;

import com.digitalholics.iotheraphy.theraphy.domain.model.entity.Treatment;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.CreateTreatmentResource;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.UpdateTreatmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TreatmentService {

    List<Treatment> getAll();

    Page<Treatment> getAll(Pageable pageable);

    Treatment getById(Integer treatmentId);

    Treatment create(CreateTreatmentResource treatmentResource);

    List<Treatment> getTreatmentByTherapyId(Integer therapyId);

    Treatment getTreatmentByDateAndTherapyId(Integer therapyId, String date);

    Treatment update(Integer treatmentId, UpdateTreatmentResource request);

    ResponseEntity<?> delete(Integer treatmentId);

}
