package com.digitalholics.iotheraphy.Profiless.domain.service;

import com.digitalholics.iotheraphy.Profiless.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profiless.resource.CreatePhysiotherapistResource;
import com.digitalholics.iotheraphy.Profiless.resource.UpdatePhysiotherapistResource;
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
