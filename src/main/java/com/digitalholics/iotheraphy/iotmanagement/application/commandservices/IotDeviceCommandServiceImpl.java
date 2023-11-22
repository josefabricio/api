package com.digitalholics.iotheraphy.iotmanagement.application.commandservices;

import com.digitalholics.iotheraphy.iotmanagement.domain.model.aggregate.IotDevice;
import com.digitalholics.iotheraphy.iotmanagement.infrastructure.persistence.jpa.respositories.IotDeviceRepository;
import com.digitalholics.iotheraphy.iotmanagement.domain.services.IotDeviceCommandService;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.CreateIotDeviceResource;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.UpdateIotDeviceResource;
import com.digitalholics.iotheraphy.shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class IotDeviceCommandServiceImpl implements IotDeviceCommandService {

    private static final String ENTITY = "IoTDevice";

    private final IotDeviceRepository iotDeviceRepository;
    private final Validator validator;

    public IotDeviceCommandServiceImpl(IotDeviceRepository iotDeviceRepository, Validator validator) {
        this.iotDeviceRepository = iotDeviceRepository;
        this.validator = validator;
    }

    @Override
    public List<IotDevice> getAll() {
        return iotDeviceRepository.findAll();
    }

    @Override
    public Page<IotDevice> getAll(Pageable pageable) {
        return iotDeviceRepository.findAll(pageable);
    }

    @Override
    public List<IotDevice> getByTherapyIdAndDate(Integer therapyId, String date) {


        return iotDeviceRepository.findIotResultsByTherapyByDate(therapyId,date);

    }



    @Override
    public IotDevice getById(Integer iotDeviceId) {
        return iotDeviceRepository.findById(iotDeviceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, iotDeviceId));
    }

    @Override
    public IotDevice getByTemperature(String temperature) {
        return iotDeviceRepository.findByTemperature(temperature);
    }

    @Override
    public IotDevice create(CreateIotDeviceResource createIotDeviceResource) {
        Set<ConstraintViolation<CreateIotDeviceResource>>
                violations = validator.validate(createIotDeviceResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        IotDevice iotDevice = new IotDevice();
        iotDevice.setTemperature(createIotDeviceResource.getTemperature());
        iotDevice.setDistance(createIotDeviceResource.getDistance());
        iotDevice.setPulse(createIotDeviceResource.getPulse());
        iotDevice.setHumidity(createIotDeviceResource.getHumidity());
        iotDevice.setTherapyId(createIotDeviceResource.getTherapyId());
        //iotDevice.setDate(createIotDeviceResource.getDate());
        return iotDeviceRepository.save(iotDevice);
    }

    @Override
    public IotDevice update(Integer iotDeviceId, UpdateIotDeviceResource request) {
        IotDevice iotDevice = getById(iotDeviceId);

        iotDevice.setTemperature(request.getTemperature() != null ? request.getTemperature() : iotDevice.getTemperature());
        iotDevice.setDistance(request.getDistance() != null ? request.getDistance() : iotDevice.getDistance());
        iotDevice.setPulse(request.getPulse() != null ? request.getPulse() : iotDevice.getPulse());
        iotDevice.setHumidity(request.getHumidity() != null ? request.getHumidity() : iotDevice.getHumidity());
        iotDevice.setTherapyId(request.getTherapyId() != null ? request.getTherapyId() : iotDevice.getId());
        iotDevice.setDate(request.getDate() != null ? request.getDate() : iotDevice.getDate());


        return iotDeviceRepository.save(iotDevice);
    }

    @Override
    public ResponseEntity<?> delete(Integer iotDeviceId) {
        return iotDeviceRepository.findById(iotDeviceId).map(iotDevice -> {
            iotDeviceRepository.delete(iotDevice);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,iotDeviceId));
    }
}
