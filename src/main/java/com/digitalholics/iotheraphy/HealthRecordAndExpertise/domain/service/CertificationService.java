package com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Certification;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification.CreateCertificationResource;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification.UpdateCertificationResource;
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
