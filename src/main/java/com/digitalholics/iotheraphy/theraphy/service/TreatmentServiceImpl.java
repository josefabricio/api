package com.digitalholics.iotheraphy.theraphy.service;

import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.shared.Exception.UnauthorizedException;
import com.digitalholics.iotheraphy.theraphy.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.theraphy.domain.model.entity.Treatment;
import com.digitalholics.iotheraphy.theraphy.domain.persistence.TherapyRepository;
import com.digitalholics.iotheraphy.theraphy.domain.persistence.TreatmentRepository;
import com.digitalholics.iotheraphy.theraphy.domain.service.TreatmentService;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.CreateTreatmentResource;
import com.digitalholics.iotheraphy.theraphy.resource.Treatment.UpdateTreatmentResource;
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
public class TreatmentServiceImpl implements TreatmentService {



    private static final String ENTITY = "Treatment";

    private final TreatmentRepository treatmentRepository;
    private final TherapyRepository therapyRepository;


    private final Validator validator;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, TherapyRepository therapyRepository, Validator validator) {
        this.treatmentRepository = treatmentRepository;
        this.therapyRepository = therapyRepository;
        this.validator = validator;
    }

    @Override
    public List<Treatment> getAll() {
        return treatmentRepository.findAll();
    }

    @Override
    public Page<Treatment> getAll(Pageable pageable) {
        return treatmentRepository.findAll(pageable);
    }

    @Override
    public Treatment getById(Integer treatmentId) {
        return treatmentRepository.findById(treatmentId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, treatmentId));
    }

//    @Override
//    public Treatment create(Treatment treatment) {
//        Set<ConstraintViolation<Treatment>> violations = validator.validate(treatment);
//
//        if(!violations.isEmpty())
//            throw new ResourceValidationException(ENTITY, violations);
//
//        return treatmentRepository.save(treatment);
//    }
//

    @Override
    public Treatment create(CreateTreatmentResource treatmentResource) {
        Set<ConstraintViolation<CreateTreatmentResource>> violations = validator.validate(treatmentResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Therapy> therapyOptional = therapyRepository.findById(treatmentResource.getTherapyId());

        Therapy therapy = therapyOptional.orElseThrow(() -> new NotFoundException("Therapy not found with ID: " + treatmentResource.getTherapyId()));

        Treatment treatment = new Treatment();

        if (therapy.getPhysiotherapist().getUser().getUsername().equals(username)){
            treatment.setTherapy(therapy);
            treatment.setDay(treatmentResource.getDay());
            treatment.setDescription(treatmentResource.getDescription());
            treatment.setTitle(treatmentResource.getTitle());
            treatment.setDuration(treatmentResource.getDuration());
            treatment.setViewed(treatmentResource.getViewed());
            treatment.setVideoUrl(treatmentResource.getVideoUrl());
            return treatmentRepository.save(treatment);
        }else {
            throw new UnauthorizedException("You do not have permission to create an treatment for this therapy.");
        }

    }


    @Override
    public List<Treatment> getTreatmentByTherapyId(Integer therapyId) {
        return treatmentRepository.findTreatmentByTherapyId(therapyId);
    }

    @Override
    public Treatment getTreatmentByDateAndTherapyId(Integer therapyId, String date) {
        return treatmentRepository.findTreatmentByDateAndTherapyId(therapyId, date);
    }

    @Override
    public Treatment update(Integer treatmentId, UpdateTreatmentResource request) {
        Treatment treatment = getById(treatmentId);
        if (request.getTitle() != null) {
            treatment.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            treatment.setDescription(request.getDescription());
        }
        if (request.getVideoUrl() != null) {
            treatment.setVideoUrl(request.getVideoUrl());
        }
        if (request.getDuration() != null) {
            treatment.setDuration(request.getDuration());
        }
        if (request.getDay() != null) {
            treatment.setDay(request.getDay());
        }
        if (request.getViewed() != null) {
            treatment.setViewed(request.getViewed());
        }



        return treatmentRepository.save(treatment);
    }

    @Override
    public ResponseEntity<?> delete(Integer treatmentId) {
        return treatmentRepository.findById(treatmentId)
                .map(treatment -> {
                    treatmentRepository.delete(treatment);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, treatmentId));
    }
}









