package com.digitalholics.iotheraphy.Appointment.service;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.CheckRequest;
import com.digitalholics.iotheraphy.Appointment.domain.persistence.CheckRequestRepository;
import com.digitalholics.iotheraphy.Appointment.domain.service.CheckRequestService;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.Profile.domain.service.PatientService;
import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.digitalholics.iotheraphy.Security.Domain.Persistence.UserRepository;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
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
public class CheckRequestServiceImpl implements CheckRequestService {

    private static final String ENTITY = "CheckRequest";

    private final CheckRequestRepository checkRequestRepository;
    private final PatientRepository patientRepository;

    //private final TherapistRepository therapistRepository;

    private final Validator validator;

    public CheckRequestServiceImpl(CheckRequestRepository checkRequestRepository, PatientRepository patientRepository, Validator validator) {
        this.checkRequestRepository = checkRequestRepository;
        this.patientRepository = patientRepository;
        //this.therapistRepository = therapistRepository;
        this.validator = validator;
    }

    @Override
    public List<CheckRequest> getAll() {
        return checkRequestRepository.findAll();
    }

    @Override
    public Page<CheckRequest> getAll(Pageable pageable) {
        return checkRequestRepository.findAll(pageable);
    }

    @Override
    public CheckRequest getById(Integer checkRequestId) {
        return checkRequestRepository.findById(checkRequestId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, checkRequestId));
    }

    @Override
    public List<CheckRequest> getByPatientId(Integer patientId) {
        List<CheckRequest> checkRequests = checkRequestRepository.findByPatientId(patientId);

        if(checkRequests.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Check Requests for this patient");

        return checkRequests;
    }

    /*
    @Override
    public List<CheckRequest> getByTherapistId(Integer therapistId) {
        List<CheckRequest> checkRequests = checkRequestRepository.findByTherapistId(therapistId);

        if(checkRequests.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Check Requests for this therapist");

        return checkRequests;
    }*/

    @Override
    public List<CheckRequest> getByStatus(Boolean status) {
        List<CheckRequest> checkRequests = checkRequestRepository.findCheckRequestsByAccepted(status);

        if(checkRequests.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Check Requests with this status");

        return checkRequests;
    }

    @Override
    public CheckRequest create(CheckRequest checkRequest) {
        Set<ConstraintViolation<CheckRequest>> violations = validator.validate(checkRequest);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Patient> patient = patientRepository.findById(checkRequest.getPatient().getId());
        //Optional<Therapist> therapist = therapistRepository.findById(checkRequest.getTherapist().getId());



        return checkRequestRepository.save(checkRequest);

    }

    @Override
    public CheckRequest update(Integer checkRequestId, CheckRequest request) {
        Set<ConstraintViolation<CheckRequest>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return checkRequestRepository.findById(checkRequestId).map( checkRequest ->
                        checkRequestRepository.save(
                                checkRequest.withTherapist(request.getTherapist()).
                                        withPatient(request.getPatient()).
                                        withAccepted(request.getAccepted()).
                                        withTopic(request.getTopic()).
                                        withDate(request.getDate()).
                                        withHour(request.getHour()).
                                        withPlace(request.getPlace())
                        ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,checkRequestId));
    }

    @Override
    public ResponseEntity<?> delete(Integer checkRequestId) {
        return checkRequestRepository.findById(checkRequestId).map(checkRequest -> {
            checkRequestRepository.delete(checkRequest);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,checkRequestId));
    }

}
