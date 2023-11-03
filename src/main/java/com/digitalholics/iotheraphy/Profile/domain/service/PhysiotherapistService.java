package com.digitalholics.iotheraphy.Profile.domain.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.resource.CreatePhysiotherapistResource;
import com.digitalholics.iotheraphy.Profile.resource.UpdatePhysiotherapistResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhysiotherapistService {
    List<Physiotherapist> getAll();
    Page<Physiotherapist> getAll(Pageable pageable);
    Physiotherapist getById(Integer patientId);
    Physiotherapist getByUserId(Integer userId);
    Physiotherapist getLoggedInPhysiotherapist();

    Physiotherapist create(CreatePhysiotherapistResource physiotherapist);
    Physiotherapist update(Integer physiotherapistId, UpdatePhysiotherapistResource request);
    ResponseEntity<?> delete(Integer physiotherapistId);
}
