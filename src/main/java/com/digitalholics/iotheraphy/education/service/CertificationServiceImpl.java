package com.digitalholics.iotheraphy.education.service;

import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.education.domain.model.entity.Certification;
import com.digitalholics.iotheraphy.education.domain.persistence.CertificationRepository;
import com.digitalholics.iotheraphy.education.domain.service.CertificationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class CertificationServiceImpl implements CertificationService {

    private static final String ENTITY = "Certification";

    private final CertificationRepository certificationRepository;

    private final Validator validator;

    public CertificationServiceImpl(CertificationRepository certificationRepositoryRepository, Validator validator) {
        this.certificationRepository = certificationRepositoryRepository;
        this.validator = validator;
    }

    @Override
    public List<Certification> getAll() {
        return certificationRepository.findAll();
    }

    @Override
    public Page<Certification> getAll(Pageable pageable) {
        return certificationRepository.findAll(pageable);
    }

    @Override
    public Certification getById(Integer certificationId) {
        return certificationRepository.findById(certificationId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, certificationId));
    }

    @Override
    public Certification create(Certification certification) {
        Set<ConstraintViolation<Certification>> violations = validator.validate(certification);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return certificationRepository.save(certification);
    }

    @Override
    public Certification update(Integer certificationId, Certification request) {
        Set<ConstraintViolation<Certification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return certificationRepository.findById(certificationId).map( certification ->
                        certificationRepository.save(
                                certification.withTitle(request.getTitle()).
                                        withSchool(request.getSchool()).
                                        withYear(request.getYear()).
                                        withPhotoUrl(request.getPhotoUrl())
                                        ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,certificationId));
    }

    @Override
    public ResponseEntity<?> delete(Integer certificationId) {
        return certificationRepository.findById(certificationId).map(certification -> {
           certificationRepository.delete(certification);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,certificationId));
    }
}
