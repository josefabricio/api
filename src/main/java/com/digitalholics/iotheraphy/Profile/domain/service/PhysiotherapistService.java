package com.digitalholics.iotheraphy.Profile.domain.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhysiotherapistService {
    List<Physiotheraphist> getAll();
    Page<Physiotheraphist> getAll(Pageable pageable);
    Physiotheraphist getById(Integer patientId);
    Physiotheraphist getByDni(String dni);
    Physiotheraphist getByUserId(Integer userId);
    Physiotheraphist create(Physiotheraphist physiotherapist);
    Physiotheraphist update(Integer physiotherapistId, Physiotheraphist request);
    ResponseEntity<?> delete(Integer physiotherapistId);
}
