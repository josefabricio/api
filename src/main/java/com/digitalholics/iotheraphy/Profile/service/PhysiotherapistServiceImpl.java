package com.digitalholics.iotheraphy.Profile.service;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
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
    public Physiotheraphist getByDni(String dni) {
        return physiotherapistRepository.findPhysiotherapistByDni(dni);
    }

    @Override
    public Physiotheraphist getByUserId(Integer userId) {
        return physiotherapistRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, userId));    }

    @Override
    public Physiotheraphist create(Physiotheraphist physiotheraphist) {
        Set<ConstraintViolation<Physiotheraphist>> violations = validator.validate(physiotheraphist);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found for username: " + username));

        physiotheraphist.setUser(user);

        Physiotheraphist physiotherapistWithDni = physiotherapistRepository.findPhysiotherapistByDni(physiotheraphist.getDni());

        if(physiotherapistWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "A physiotherapist with the same dni already exists.");

        return physiotherapistRepository.save(physiotheraphist);    }

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
