package com.digitalholics.iotheraphy.education.domain.service;

import com.digitalholics.iotheraphy.education.domain.model.entity.Certification;
import com.digitalholics.iotheraphy.education.resource.CreateCertificationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CertificationService {
    List<Certification> getAll();
    Page<Certification> getAll(Pageable pageable);
    Certification getById(Integer certificationId);

    List<Certification> getByPhysiotherapistId(Integer physiotherapistId);

    Certification create(CreateCertificationResource certification);
    Certification update(Integer certificationId, Certification request);
    ResponseEntity<?> delete(Integer certificationId);
}
