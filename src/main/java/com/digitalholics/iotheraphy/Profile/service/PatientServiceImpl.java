package com.digitalholics.iotheraphy.Profile.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.Profile.domain.service.PatientService;
import com.digitalholics.iotheraphy.Profile.resource.CreatePatientResource;
import com.digitalholics.iotheraphy.Profile.resource.UpdatePatientResource;
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
import org.springframework.security.core.userdetails.UserDetails;
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
    public Patient getLoggedInPatient() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            return patientRepository.findPatientByDni(username);
        }
        throw new ResourceNotFoundException("No se encontró un paciente autenticado.");
    }

    @Override
    public Patient create(CreatePatientResource patientResource) {
        Set<ConstraintViolation<CreatePatientResource>> violations = validator.validate(patientResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found for username: " + username));



        Patient patientWithDni = patientRepository.findPatientByDni(patientResource.getDni());

        if(patientWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "A patient with the same Dni first name already exists.");

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setAge(patientResource.getAge());
        patient.setDni(patientResource.getDni());
        patient.setLocation(patientResource.getLocation());
        patient.setBirthdayDate(patientResource.getBirthdayDate());
        patient.setPhotoUrl(patientResource.getPhotoUrl());
        patient.setAppointmentQuantity(0);

        return patientRepository.save(patient);

    }

    @Override
    public Patient update(Integer patientId, UpdatePatientResource request) {
        Patient patient = getById(patientId);

        patient.setDni(request.getDni() != null ? request.getDni() : patient.getDni());
        patient.setAge(request.getAge() != null ? request.getAge() : patient.getAge());
        patient.setPhotoUrl(request.getPhotoUrl() != null ? request.getPhotoUrl() : patient.getPhotoUrl());
        patient.setAppointmentQuantity(request.getAppointmentQuantity() != null ? request.getAppointmentQuantity() : patient.getAppointmentQuantity());
        patient.setLocation(request.getLocation() != null ? request.getLocation() : patient.getLocation());
        patient.setBirthdayDate(request.getBirthdayDate() != null ? request.getBirthdayDate() : patient.getBirthdayDate());

        return patientRepository.save(patient);
    }

    @Override
    public ResponseEntity<?> delete(Integer patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,patientId));
    }


}