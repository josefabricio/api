package com.digitalholics.iotheraphy.IoTAccessManagement.service;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.Result;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence.IotDeviceRepository;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence.ResultRepository;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.service.ResultService;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateResultResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateResultResource;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import com.digitalholics.iotheraphy.Therapy.domain.persistence.TreatmentRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ResultServiceImpl implements ResultService {


    private static final String ENTITY = "Results";

    private final ResultRepository resultRepository;
    private final TreatmentRepository treatmentRepository;
    private final IotDeviceRepository iotDeviceRepository;
    private final Validator validator;

    public ResultServiceImpl(ResultRepository resultRepository, TreatmentRepository treatmentRepository, IotDeviceRepository iotDeviceRepository, Validator validator) {
        this.resultRepository = resultRepository;
        this.treatmentRepository = treatmentRepository;
        this.iotDeviceRepository = iotDeviceRepository;
        this.validator = validator;
    }

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public Page<Result> getAll(Pageable pageable) {
        return resultRepository.findAll(pageable);
    }

    @Override
    public Result getById(Integer resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, resultId));
    }

    @Override
    public Result getByTreatmentId(Integer treatmentId) {
        return resultRepository.findByTreatmentId(treatmentId);
    }

    @Override
    public Result create(CreateResultResource resultResource) {
        Set<ConstraintViolation<CreateResultResource>>
                violations = validator.validate(resultResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Treatment treatment = treatmentRepository.findById(resultResource.getTreatmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Treatment", resultResource.getTreatmentId()));

        IotDevice iotDevice = iotDeviceRepository.findById(resultResource.getIotDeviceId())
                .orElseThrow(() -> new ResourceNotFoundException("IotDevice", resultResource.getIotDeviceId()));

        Result result = new Result();
        result.setTreatment(treatment);
        result.setIotDevice(iotDevice);
        result.setDate(resultResource.getDate());

        return resultRepository.save(result);
    }

    @Override
    public Result update(Integer resultId, UpdateResultResource request) {
        Result result = getById(resultId);

        result.setDate(request.getDate() != null ? request.getDate() : result.getDate());

        return resultRepository.save(result);
    }

    @Override
    public ResponseEntity<?> delete(Integer resultId) {
        return resultRepository.findById(resultId).map(result -> {
            resultRepository.delete(result);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,resultId));
    }
}
