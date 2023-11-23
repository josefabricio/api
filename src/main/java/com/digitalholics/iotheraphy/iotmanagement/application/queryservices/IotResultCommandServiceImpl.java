package com.digitalholics.iotheraphy.iotmanagement.application.queryservices;

import com.digitalholics.iotheraphy.iotmanagement.domain.model.entities.IotResult;
import com.digitalholics.iotheraphy.iotmanagement.infrastructure.persistence.jpa.respositories.IotDeviceRepository;
import com.digitalholics.iotheraphy.iotmanagement.infrastructure.persistence.jpa.respositories.IotResultRepository;
import com.digitalholics.iotheraphy.iotmanagement.domain.services.IotDeviceQueryService;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.CreateIotResultResource;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.UpdateIotResultResource;
import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
import com.digitalholics.iotheraphy.theraphy.domain.persistence.TreatmentRepository;
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
public class IotResultCommandServiceImpl implements IotDeviceQueryService {


    private static final String ENTITY = "IotResult";

    private final IotResultRepository iotResultRepository;
    private final TreatmentRepository treatmentRepository;
    private final IotDeviceRepository iotDeviceRepository;
    private final Validator validator;

    public IotResultCommandServiceImpl(IotResultRepository iotResultRepository, TreatmentRepository treatmentRepository, IotDeviceRepository iotDeviceRepository, Validator validator) {
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
