package com.digitalholics.iotheraphy.Profile.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.Profile.domain.service.PhysiotherapistService;
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
    public Physiotherapist create(Physiotherapist physiotherapist) {
        Set<ConstraintViolation<Physiotherapist>> violations = validator.validate(physiotherapist);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found for username: " + username));

        physiotherapist.setUser(user);

        Physiotherapist physiotherapistWithDni = physiotherapistRepository.findPhysiotherapistByDni(physiotherapist.getDni());

        if(physiotherapistWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "A physiotherapist with the same Dni first name already exists.");

        return physiotherapistRepository.save(physiotherapist);    }

    @Override
    public Physiotherapist update(Integer patientId, Physiotherapist request) {
        Set<ConstraintViolation<Physiotherapist>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return physiotherapistRepository.findById(patientId).map( physiotherapist ->
                        physiotherapistRepository.save(
                                physiotherapist.withAge(request.getAge()).
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
        return physiotherapistRepository.findById(patientId).map(physiotherapist -> {
            physiotherapistRepository.delete(physiotherapist);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,patientId));    }
}
