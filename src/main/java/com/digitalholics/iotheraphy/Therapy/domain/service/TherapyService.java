package com.digitalholics.iotheraphy.Therapy.domain.service;

import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.CreateTherapyResource;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.UpdateTherapyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TherapyService {

    List<Therapy> getAll();

    Page<Therapy> getAll(Pageable pageable);

    Therapy getById(Integer therapyId);

    Therapy create(CreateTherapyResource therapy);
    Therapy update(Integer therapyId, UpdateTherapyResource request);

    ResponseEntity<?> delete(Integer therapyId);

}
