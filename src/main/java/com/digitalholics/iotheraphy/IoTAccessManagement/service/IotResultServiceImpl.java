package com.digitalholics.iotheraphy.IoTAccessManagement.service;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotResult;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence.IotDeviceRepository;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence.IotResultRepository;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.service.IotResultService;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateIotResultResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateIotResultResource;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class IotResultServiceImpl implements IotResultService {


    private static final String ENTITY = "IotResult";

    private final IotResultRepository iotResultRepository;
    private final TreatmentRepository treatmentRepository;
    private final IotDeviceRepository iotDeviceRepository;
    private final Validator validator;

    public IotResultServiceImpl(IotResultRepository iotResultRepository, TreatmentRepository treatmentRepository, IotDeviceRepository iotDeviceRepository, Validator validator) {
        this.iotResultRepository = iotResultRepository;
        this.treatmentRepository = treatmentRepository;
        this.iotDeviceRepository = iotDeviceRepository;
        this.validator = validator;
    }

    @Override
    public List<IotResult> getAll() {
        return iotResultRepository.findAll();
    }

    @Override
    public Page<IotResult> getAll(Pageable pageable) {
        return iotResultRepository.findAll(pageable);
    }

    @Override
    public IotResult getById(Integer resultId) {
        return iotResultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, resultId));
    }

    @Override
    public IotResult getByTherapyId(String therapyId) {
        return iotResultRepository.findByIotDeviceId(therapyId);
    }

    @Override
    public List<IotResult> getByTherapyIdAndDate(String therapyId, String date) {

        return iotResultRepository.findByTherapyByDate(therapyId,date);

    }

    @Override
    public IotResult create(CreateIotResultResource resultResource) {
        Set<ConstraintViolation<CreateIotResultResource>>
                violations = validator.validate(resultResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        IotResult iotResult = new IotResult();
        iotResult.setIotDeviceId(resultResource.getIotDeviceId());
        iotResult.setHumidity(resultResource.getHumidity());
        iotResult.setTemperature(resultResource.getTemperature());
        iotResult.setPulse(resultResource.getPulse());
        iotResult.setMapAmplitude(resultResource.getMapAmplitude());
        iotResult.setMapFrequency(resultResource.getMapFrequency());
        iotResult.setMapDuration(resultResource.getMapDuration());

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        iotResult.setDate(formattedDate);

        return iotResultRepository.save(iotResult);
    }

    @Override
    public IotResult update(Integer resultId, UpdateIotResultResource request) {
        IotResult iotResult = getById(resultId);

        iotResult.setDate(request.getDate() != null ? request.getDate() : iotResult.getDate());

        return iotResultRepository.save(iotResult);
    }

    @Override
    public ResponseEntity<?> delete(Integer resultId) {
        return iotResultRepository.findById(resultId).map(iotResult -> {
            iotResultRepository.delete(iotResult);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,resultId));
    }
}
