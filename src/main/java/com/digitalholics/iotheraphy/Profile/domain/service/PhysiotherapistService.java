package com.digitalholics.iotheraphy.Profile.domain.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhysiotherapistService {
    List<Physiotherapist> getAll();
    Page<Physiotherapist> getAll(Pageable pageable);
    Physiotherapist getById(Integer patientId);
    Physiotherapist getByUserId(Integer userId);
    Physiotherapist create(Physiotherapist physiotherapist);
    Physiotherapist update(Integer physiotherapistId, Physiotherapist request);
    ResponseEntity<?> delete(Integer physiotherapistId);
}
