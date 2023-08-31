package com.digitalholics.iotheraphy.Profile.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Profile.domain.service.PatientService;
import com.digitalholics.iotheraphy.Profile.domain.service.PhysiotherapistService;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService {
    private static final String ENTITY = "Physiotherapist";

    private final PhysiotherapistRepository physiotherapistRepository;


    private final Validator validator;

    public PhysiotherapistServiceImpl(PhysiotherapistRepository physiotherapistRepository, Validator validator) {
        this.physiotherapistRepository = physiotherapistRepository;
        this.validator = validator;
    }

    @Override
    public List<Physiotheraphist> getAll() {
        return physiotherapistRepository.findAll();
    }

    @Override
    public Page<Physiotheraphist> getAll(Pageable pageable) {
        return physiotherapistRepository.findAll(pageable);
    }

    @Override
    public Physiotheraphist getById(Integer patientId) {
        return physiotherapistRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, patientId));    }

    @Override
    public Physiotheraphist getByUserId(Integer userId) {
        return physiotherapistRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, userId));    }

    @Override
    public Physiotheraphist create(Physiotheraphist patient) {
        Set<ConstraintViolation<Physiotheraphist>> violations = validator.validate(patient);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return physiotherapistRepository.save(patient);    }

    @Override
    public Physiotheraphist update(Integer patientId, Physiotheraphist request) {
        Set<ConstraintViolation<Physiotheraphist>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return physiotherapistRepository.findById(patientId).map( physiotheraphist ->
                        physiotherapistRepository.save(
                                physiotheraphist.withAge(request.getAge()).
                                        withPhotoUrl(request.getPhotoUrl()).
                                        withBirthdayDate(request.getBirthdayDate()).
                                        withSpecialization(request.getSpecialization()).
                                        withLocation(request.getLocation()).
                                        withRating(request.getRating()).
                                        withConsultationQuantity(request.getConsultationQuantity()).
                                        withPatientQuantity(request.getPatientQuantity()).
                                        withYearsExperience(request.getYearsExperience()).
                                        withFees(request.getFees())
                                )
                        )
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,patientId));    }

    @Override
    public ResponseEntity<?> delete(Integer patientId) {
        return physiotherapistRepository.findById(patientId).map(physiotheraphist -> {
            physiotherapistRepository.delete(physiotheraphist);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,patientId));    }
}
