package com.digitalholics.iotheraphy.HealthRecordAndExpertise.service;

import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Job;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.UpdateCertificationResource;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.model.entity.Certification;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.persistence.CertificationRepository;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.domain.service.CertificationService;
import com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification.CreateCertificationResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CertificationServiceImpl implements CertificationService {

    private static final String ENTITY = "Certification";

    private final CertificationRepository certificationRepository;
    private final PhysiotherapistRepository  physiotherapistRepository;
    private final Validator validator;

    public CertificationServiceImpl(CertificationRepository certificationRepositoryRepository, PhysiotherapistRepository physiotherapistRepository, Validator validator) {
        this.certificationRepository = certificationRepositoryRepository;
        this.physiotherapistRepository = physiotherapistRepository;
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
    public List<Certification> getByPhysiotherapistId(Integer physiotherapistId) {
        List<Certification> certifications = certificationRepository.findByPhysiotherapistId(physiotherapistId);

        if(certifications.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Certifications for this physiotherapist");

        return certifications;
    }

    @Override
    public Certification create(CreateCertificationResource certificationResource) {
        Set<ConstraintViolation<CreateCertificationResource>> violations = validator.validate(certificationResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Physiotherapist> physiotherapistOptional = Optional.ofNullable(physiotherapistRepository.findPhysiotherapistByUserUsername(username));
        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(()->new NotFoundException("This physiotherapist not found with ID: "+ username));

        Certification certification = new Certification();
        certification.setPhysiotherapist(physiotherapist);
        certification.setTitle(certificationResource.getTitle());
        certification.setSchool(certificationResource.getSchool());
        certification.setYear(certificationResource.getYear());

        return certificationRepository.save(certification);
    }

    @Override
    public Certification update(Integer certificationId, UpdateCertificationResource request) {
        Certification certification = getById(certificationId);

        if (request.getTitle() != null) {
            certification.setTitle(request.getTitle());
        }
        if (request.getSchool() != null) {
            certification.setSchool(request.getSchool());
        }
        if (request.getYear() != null) {
            certification.setYear(request.getYear());
        }

        return certificationRepository.save(certification);
    }

    @Override
    public ResponseEntity<?> delete(Integer certificationId) {
        return certificationRepository.findById(certificationId).map(certification -> {
           certificationRepository.delete(certification);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,certificationId));
    }
}
