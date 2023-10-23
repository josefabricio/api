package com.digitalholics.iotheraphy.IoTAccessManagement.service;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.persistence.IotDeviceRepository;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.service.IotDeviceService;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateIotDeviceResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateIotDeviceResource;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceNotFoundException;
import com.digitalholics.iotheraphy.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public class IotDeviceServiceImpl implements IotDeviceService {

    private static final String ENTITY = "IoTDevice";

    private final IotDeviceRepository iotDeviceRepositoryl;
    private final Validator validator;

    public IotDeviceServiceImpl(IotDeviceRepository iotDeviceRepositoryl, Validator validator) {
        this.iotDeviceRepositoryl = iotDeviceRepositoryl;
        this.validator = validator;
    }

    @Override
    public List<IotDevice> getAll() {
        return iotDeviceRepositoryl.findAll();
    }

    @Override
    public Page<IotDevice> getAll(Pageable pageable) {
        return iotDeviceRepositoryl.findAll(pageable);
    }

    @Override
    public IotDevice getById(Integer iotDeviceId) {
        return iotDeviceRepositoryl.findById(iotDeviceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, iotDeviceId));
    }

    @Override
    public IotDevice getByTemperature(String temperature) {
        return iotDeviceRepositoryl.findByTemperature(temperature);
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

        return iotDeviceRepositoryl.save(iotDevice);
    }

    @Override
    public IotDevice update(Integer iotDeviceId, UpdateIotDeviceResource request) {
        IotDevice iotDevice = getById(iotDeviceId);

        iotDevice.setTemperature(request.getTemperature() != null ? request.getTemperature() : iotDevice.getTemperature());
        iotDevice.setDistance(request.getDistance() != null ? request.getDistance() : iotDevice.getDistance());
        iotDevice.setPulse(request.getPulse() != null ? request.getPulse() : iotDevice.getPulse());
        iotDevice.setHumidity(request.getHumidity() != null ? request.getHumidity() : iotDevice.getHumidity());

        return iotDeviceRepositoryl.save(iotDevice);
    }

    @Override
    public ResponseEntity<?> delete(Integer iotDeviceId) {
        return iotDeviceRepositoryl.findById(iotDeviceId).map(iotDevice -> {
            iotDeviceRepositoryl.delete(iotDevice);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,iotDeviceId));
    }
}
