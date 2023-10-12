package com.digitalholics.iotheraphy.Profile.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Profile.domain.service.PhysiotherapistService;
import com.digitalholics.iotheraphy.Profile.resource.CreatePhysiotherapistResource;
import com.digitalholics.iotheraphy.Profile.resource.UpdatePhysiotherapistResource;
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
public class PhysiotherapistServiceImpl implements PhysiotherapistService {
    private static final String ENTITY = "Physiotherapist";

    private final PhysiotherapistRepository physiotherapistRepository;
    private final UserRepository userRepository;


    private final Validator validator;

    public PhysiotherapistServiceImpl(PhysiotherapistRepository physiotherapistRepository, UserRepository userRepository, Validator validator) {
        this.physiotherapistRepository = physiotherapistRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<Physiotherapist> getAll() {
        return physiotherapistRepository.findAll();
    }

    @Override
    public Page<Physiotherapist> getAll(Pageable pageable) {
        return physiotherapistRepository.findAll(pageable);
    }

    @Override
    public Physiotherapist getById(Integer patientId) {
        return physiotherapistRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, patientId));    }

    @Override
    public Physiotherapist getByUserId(Integer userId) {
        return physiotherapistRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, userId));    }

    @Override
    public Physiotherapist create(CreatePhysiotherapistResource physiotherapistResource) {
        Set<ConstraintViolation<CreatePhysiotherapistResource>> violations = validator.validate(physiotherapistResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found for username: " + username));



        Physiotherapist physiotherapistWithDni = physiotherapistRepository.findPhysiotherapistByDni(physiotherapistResource.getDni());

        if(physiotherapistWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "A physiotherapist with the same Dni first name already exists.");

        Physiotherapist physiotherapist = new Physiotherapist();
        physiotherapist.setUser(user);
        physiotherapist.setAge(physiotherapistResource.getAge());
        physiotherapist.setDni(physiotherapistResource.getDni());
        physiotherapist.setLocation(physiotherapistResource.getLocation());
        physiotherapist.setBirthdayDate(physiotherapistResource.getBirthdayDate());
        physiotherapist.setPhotoUrl(" ");
        physiotherapist.setConsultationQuantity(0);
        physiotherapist.setSpecialization(physiotherapistResource.getSpecialization());
        physiotherapist.setYearsExperience(physiotherapistResource.getYearsExperience());
        physiotherapist.setRating(0.0);
        physiotherapist.setPatientQuantity(0);
        physiotherapist.setFees(physiotherapistResource.getFees());

        return physiotherapistRepository.save(physiotherapist);    }

    @Override
    public Physiotherapist update(Integer physiotherapistId, UpdatePhysiotherapistResource request) {
        Physiotherapist physiotherapist = getById(physiotherapistId);

        if (request.getDni() != null) {
            physiotherapist.setDni(request.getDni());
        }
        if (request.getAge() != null) {
            physiotherapist.setAge(request.getAge());
        }
        if (request.getPhotoUrl() != null) {
            physiotherapist.setPhotoUrl(request.getPhotoUrl());
        }
        if (request.getPatientQuantity() != null) {
            physiotherapist.setPatientQuantity(request.getPatientQuantity());
        }
        if (request.getLocation() != null) {
            physiotherapist.setLocation(request.getLocation());
        }
        if (request.getBirthdayDate() != null) {
            physiotherapist.setBirthdayDate(request.getBirthdayDate());
        }
        if (request.getRating() != null) {
            physiotherapist.setRating(request.getRating());
        }
        if (request.getSpecialization() != null) {
            physiotherapist.setSpecialization(request.getSpecialization());
        }
        if (request.getYearsExperience() != null) {
            physiotherapist.setYearsExperience(request.getYearsExperience());
        }
        if (request.getConsultationQuantity() != null) {
            physiotherapist.setConsultationQuantity(request.getConsultationQuantity());
        }
        if (request.getFees() != null) {
            physiotherapist.setFees(request.getFees());
        }

        return physiotherapistRepository.save(physiotherapist);
    }

    @Override
    public ResponseEntity<?> delete(Integer physiotherapistId) {
        return physiotherapistRepository.findById(physiotherapistId).map(physiotherapist -> {
            physiotherapistRepository.delete(physiotherapist);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,physiotherapistId));    }
}
