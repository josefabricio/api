package com.digitalholics.iotheraphy.Theraphy.domain.service;

import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TheraphyService {

    List<Theraphy> getAll();

    Page<Theraphy> getAll(Pageable pageable);

    Theraphy getById(Integer theraphyId);

    Theraphy create(Theraphy theraphy);

    Theraphy update(Integer theraphyId, Theraphy request);

    ResponseEntity<?> delete(Integer theraphyId);

}
