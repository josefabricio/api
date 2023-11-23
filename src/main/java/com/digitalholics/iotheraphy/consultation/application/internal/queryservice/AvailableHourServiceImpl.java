package com.digitalholics.iotheraphy.consultation.application.internal.queryservice;

import com.digitalholics.iotheraphy.consultation.domain.model.entities.AvailableHour;
import com.digitalholics.iotheraphy.consultation.infrastructure.persistence.jpa.repositories.AvailableHourRepository;
import com.digitalholics.iotheraphy.consultation.domain.services.AvailableHourService;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.CreateAvailableHourResource;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.UpdateAvailableHourResource;
import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Physiotherapist;
import com.digitalholics.iotheraphy.profile.infrastructure.jpa.repositories.PhysiotherapistRepository;
import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
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
public class AvailableHourServiceImpl implements AvailableHourService {

    private static final String ENTITY = "AvailableHour";
    private final AvailableHourRepository availableHourRepository;
    private final PhysiotherapistRepository physiotherapistRepository;
    private final Validator validator;

    public AvailableHourServiceImpl(AvailableHourRepository availableHourRepository, PhysiotherapistRepository physiotherapistRepository, Validator validator) {
        this.availableHourRepository = availableHourRepository;
        this.physiotherapistRepository = physiotherapistRepository;
        this.validator = validator;
    }


    @Override
    public List<AvailableHour> getAll() {
        return availableHourRepository.findAll();
    }

    @Override
    public Page<AvailableHour> getAll(Pageable pageable) {
        return availableHourRepository.findAll(pageable);
    }

    @Override
    public AvailableHour getById(Integer availableHourId) {
        return availableHourRepository.findById(availableHourId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, availableHourId));
    }

    @Override
    public List<AvailableHour> getByPhysiotherapistId(Integer physiotherapistId) {
        List<AvailableHour> availableHours = availableHourRepository.findByPhysiotherapistId(physiotherapistId);

        if(availableHours.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Available Hours for this physiotherapist");

        return availableHours;
    }

    @Override
    public AvailableHour create(CreateAvailableHourResource availableHourResource) {
        Set<ConstraintViolation<CreateAvailableHourResource>> violations = validator.validate(availableHourResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Physiotherapist> physiotherapistOptional = Optional.ofNullable(physiotherapistRepository.findPhysiotherapistByUserUsername(username));
        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(() -> new NotFoundException("Not found physiotherapist with email: " + username));

//            Optional<Physiotherapist> physiotherapistOptional = physiotherapistRepository.findById(availableHourResource.getPhysiotherapistId());
//            Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(() -> new NotFoundException("Not found physiotherapist with ID: " + availableHourResource.getPhysiotherapistId()));

        AvailableHour availableHour = new AvailableHour();
        availableHour.setDay(availableHourResource.getDay());
        availableHour.setHours(availableHourResource.getHours());
        availableHour.setPhysiotherapist(physiotherapist);

        return availableHourRepository.save(availableHour);
    }

    @Override
    public AvailableHour update(Integer availableHourId, UpdateAvailableHourResource request) {
        Set<ConstraintViolation<UpdateAvailableHourResource>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        return availableHourRepository.findById(availableHourId).map(availableHour ->
                        availableHourRepository.save(
                                availableHour.withHours(request.getHours())
                                        .withDay(request.getDay()))).orElseThrow(() -> new ResourceNotFoundException(ENTITY, availableHourId));
    }

    @Override
    public ResponseEntity<?> delete(Integer availableHourId) {
        return availableHourRepository.findById(availableHourId).map(availableHour -> {
            availableHourRepository.delete(availableHour);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,availableHourId));
    }
}
