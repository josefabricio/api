package com.digitalholics.iotheraphy.Profile.service;

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
public class PatientServiceImpl implements PatientService {

    private static final String ENTITY = "Patient";

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;


    private final Validator validator;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository, Validator validator) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient getById(Integer patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public Patient getByDni(String dni) {
        return patientRepository.findPatientByDni(dni);
    }

    @Override
    public Patient getByUserId(Integer userId) {
        return patientRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public Patient create(Patient patient) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found for username: " + username));

        patient.setUser(user);

        Patient patientWithDni = patientRepository.findPatientByDni(patient.getDni());

        if(patientWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "A patient with the same first name already exists.");

        return patientRepository.save(patient);

    }

    @Override
    public Patient update(Integer patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map( patient ->
                        patientRepository.save(
                                patient.withAge(request.getAge()).
                                        withPhotoUrl(request.getPhotoUrl()).
                                        withBirthdayDate(request.getBirthdayDate()).
                                        withAppointmentQuantity(request.getAppointmentQuantity())
                                ))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,patientId));
    }

    @Override
    public ResponseEntity<?> delete(Integer patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,patientId));
    }


}