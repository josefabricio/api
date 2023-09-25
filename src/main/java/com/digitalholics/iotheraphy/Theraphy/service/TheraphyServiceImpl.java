package com.digitalholics.iotheraphy.Theraphy.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.digitalholics.iotheraphy.Theraphy.domain.persistence.TheraphyRepository;
import com.digitalholics.iotheraphy.Theraphy.domain.service.TheraphyService;
import com.digitalholics.iotheraphy.Theraphy.resource.CreateTheraphyResource;
import com.digitalholics.iotheraphy.Theraphy.resource.TheraphyResource;
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
public class TheraphyServiceImpl implements TheraphyService {


    private static final String ENTITY = "Theraphy";

    private final TheraphyRepository theraphyRepository;

    private final PatientRepository patientRepository;

    private final PhysiotherapistRepository physiotherapistRepository;

    private final Validator validator;

    public TheraphyServiceImpl(TheraphyRepository theraphyRepository, PatientRepository patientRepository, PhysiotherapistRepository physiotherapistRepository, Validator validator) {
        this.theraphyRepository = theraphyRepository;
        this.patientRepository = patientRepository;
        this.physiotherapistRepository = physiotherapistRepository;
        this.validator = validator;
    }



    @Override
    public List<Theraphy> getAll() {
        return theraphyRepository.findAll();
    }

    @Override
    public Page<Theraphy> getAll(Pageable pageable) {
        return theraphyRepository.findAll(pageable);
    }

    @Override
    public Theraphy getById(Integer theraphyId) {
        return theraphyRepository.findById(theraphyId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, theraphyId));
    }

    @Override
    public Theraphy create(CreateTheraphyResource theraphyResource) {
       Set<ConstraintViolation<CreateTheraphyResource>> violations = validator.validate(theraphyResource);

       if(!violations.isEmpty())
           throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Patient> patientOptional = patientRepository.findById(theraphyResource.getPatientId());
        Optional<Physiotheraphist> physiotheraphistOptional = Optional.ofNullable(physiotherapistRepository.findPhysiotheraphistByUserUsername(username));

        Patient patient = patientOptional.orElseThrow(()-> new NotFoundException("This patient not found with ID: "+ theraphyResource.getPatientId()));
        Physiotheraphist physiotheraphist = physiotheraphistOptional.orElseThrow(()->new NotFoundException("This physiotherapist not found with ID: "+ username));

        Theraphy theraphy = new Theraphy();
        theraphy.setTheraphyName(theraphyResource.getTheraphyName());
        theraphy.setAppointmentQuantity(theraphyResource.getAppointmentQuantity());
        theraphy.setAppointmentGap(theraphyResource.getAppointmentGap());
        theraphy.setStartAt(theraphyResource.getStartAt());
        theraphy.setFinishAt(theraphyResource.getFinishAt());
        theraphy.setPatientId(patient);
        theraphy.setPhysiotheraphistId(physiotheraphist);

       return theraphyRepository.save(theraphy);
    }

    @Override
    public Theraphy update(Integer theraphyId, Theraphy request) {
        Set<ConstraintViolation<Theraphy>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return theraphyRepository.findById(theraphyId).map(theraphy ->
                theraphyRepository.save(
                        theraphy.withTheraphyName(request.getTheraphyName())
                                .withAppointmentGap(request.getAppointmentGap())
                                .withStartAt(request.getStartAt())
                                .withFinishAt(request.getFinishAt())
                                .withAppointmentQuantity(request.getAppointmentQuantity())
                )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, theraphyId));


    }

    @Override
    public ResponseEntity<?> delete(Integer theraphyId) {
        return theraphyRepository.findById(theraphyId)
                .map(theraphy -> {
                    theraphyRepository.delete(theraphy);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, theraphyId));
    }
}
