package com.digitalholics.iotheraphy.Therapy.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PatientRepository;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.digitalholics.iotheraphy.Security.Domain.Persistence.UserRepository;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Therapy;
import com.digitalholics.iotheraphy.Therapy.domain.persistence.TherapyRepository;
import com.digitalholics.iotheraphy.Therapy.domain.service.TherapyService;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.CreateTherapyResource;
import com.digitalholics.iotheraphy.Therapy.resource.Therapy.UpdateTherapyResource;
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
public class TherapyServiceImpl implements TherapyService {


    private static final String ENTITY = "Therapy";

    private final TherapyRepository therapyRepository;

    private final PatientRepository patientRepository;

    private final PhysiotherapistRepository physiotherapistRepository;

    private final UserRepository userRepository;

    private final Validator validator;

    public TherapyServiceImpl(TherapyRepository therapyRepository, PatientRepository patientRepository, PhysiotherapistRepository physiotherapistRepository, UserRepository userRepository, Validator validator) {
        this.therapyRepository = therapyRepository;
        this.patientRepository = patientRepository;
        this.physiotherapistRepository = physiotherapistRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }



    @Override
    public List<Therapy> getAll() {
        return therapyRepository.findAll();
    }

    @Override
    public Page<Therapy> getAll(Pageable pageable) {
        return therapyRepository.findAll(pageable);
    }

    @Override
    public List<Therapy> getTherapyByPatientId(Integer patientId) {
        return therapyRepository.findTherapyByPatientId(patientId);
    }

    @Override
    public Therapy getActiveTherapyByPatientId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            Optional<User> userOptional = userRepository.findByUsername(username);
            User user = userOptional.orElseThrow(() -> new NotFoundException("User not found for username: " + username));
            Optional<Patient> patientOptional = patientRepository.findByUserId(user.getId());
            Patient patient = patientOptional.orElseThrow(() -> new NotFoundException("User not found patient for username: " + username));

            return therapyRepository.findActiveTherapyByPatientId(patient.getId());
        }
        throw new ResourceNotFoundException("No se encontró un paciente autenticado.");
    }

    @Override
    public Therapy getTherapyByPhysiotherapistIdAndPatientId(Integer physiotherapistId, Integer patientId) {
        return therapyRepository.findTherapyByPhysiotherapistIdAndPatientId(physiotherapistId, patientId);
    }

    @Override
    public Therapy getById(Integer therapyId) {
        return therapyRepository.findById(therapyId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, therapyId));
    }

    @Override
    public Therapy create(CreateTherapyResource therapyResource) {
       Set<ConstraintViolation<CreateTherapyResource>> violations = validator.validate(therapyResource);

       if(!violations.isEmpty())
           throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Patient> patientOptional = patientRepository.findById(therapyResource.getPatientId());
        Optional<Physiotherapist> physiotherapistOptional = Optional.ofNullable(physiotherapistRepository.findPhysiotherapistByUserUsername(username));

        Patient patient = patientOptional.orElseThrow(()-> new NotFoundException("This patient not found with ID: "+ therapyResource.getPatientId()));
        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(()->new NotFoundException("This physiotherapist not found with ID: "+ username));

        Therapy therapy = new Therapy();
        therapy.setTherapyName(therapyResource.getTherapyName());
        therapy.setDescription(therapyResource.getDescription());
        therapy.setAppointmentQuantity(therapyResource.getAppointmentQuantity());
        therapy.setStartAt(therapyResource.getStartAt());
        therapy.setFinishAt(therapyResource.getFinishAt());
        therapy.setFinished(therapyResource.getFinished());
        therapy.setPatient(patient);
        therapy.setPhysiotherapist(physiotherapist);



       return therapyRepository.save(therapy);
    }

    @Override
    public Therapy update(Integer therapyId, UpdateTherapyResource request) {
        Therapy therapy = getById(therapyId);

        if (request.getTherapyName() != null) {
            therapy.setTherapyName(request.getTherapyName());
        }
        if (request.getAppointmentQuantity() != null) {
            therapy.setAppointmentQuantity(request.getAppointmentQuantity());
        }
        if (request.getStartAt() != null) {
            therapy.setStartAt(request.getStartAt());
        }
        if (request.getFinishAt() != null) {
            therapy.setFinishAt(request.getFinishAt());
        }


        return therapyRepository.save(therapy);
    }

    @Override
    public ResponseEntity<?> delete(Integer therapyId) {
        return therapyRepository.findById(therapyId)
                .map(therapy -> {
                    therapyRepository.delete(therapy);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, therapyId));
    }
}
