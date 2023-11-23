package com.digitalholics.iotheraphy.profile.domain.services;

import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Physiotherapist;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.CreatePhysiotherapistResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.UpdatePhysiotherapistResource;
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
