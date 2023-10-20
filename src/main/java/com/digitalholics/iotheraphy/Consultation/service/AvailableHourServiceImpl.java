package com.digitalholics.iotheraphy.Consultation.service;

import com.digitalholics.iotheraphy.Consultation.domain.model.entity.AvailableHour;
import com.digitalholics.iotheraphy.Consultation.domain.persistence.AvailableHourRepository;
import com.digitalholics.iotheraphy.Consultation.domain.service.AvailableHourService;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.CreateAvailableHourResource;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.UpdateAvailableHourResource;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.domain.persistence.PhysiotherapistRepository;
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

//        Optional<Physiotherapist> physiotherapistOptional = physiotherapistRepository.findById(availableHourResource.getPhysiotherapistId());
//        Physiotherapist physiotherapist = physiotherapistOptional.orElseThrow(() -> new NotFoundException("Not found physiotherapist with ID: " + availableHourResource.getPhysiotherapistId()));

        AvailableHour availableHour = new AvailableHour();
        availableHour.setDays(availableHourResource.getDays());
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
                                        .withDays(request.getDays()))).orElseThrow(() -> new ResourceNotFoundException(ENTITY, availableHourId));
    }

    @Override
    public ResponseEntity<?> delete(Integer availableHourId) {
        return availableHourRepository.findById(availableHourId).map(availableHour -> {
            availableHourRepository.delete(availableHour);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,availableHourId));
    }
}
