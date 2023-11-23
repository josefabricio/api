package com.digitalholics.iotheraphy.profile.domain.services;

import com.digitalholics.iotheraphy.profile.domain.model.entities.Certification;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification.CreateCertificationResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification.UpdateCertificationResource;
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
    Certification update(Integer certificationId, UpdateCertificationResource request);
    ResponseEntity<?> delete(Integer certificationId);
}
