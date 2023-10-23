package com.digitalholics.iotheraphy.IoTAccessManagement.domain.service;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateIotDeviceResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateIotDeviceResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IotDeviceService {
    List<IotDevice> getAll();
    Page<IotDevice> getAll(Pageable pageable);
    IotDevice getById(Integer iotDeviceId);
    IotDevice getByTemperature(String temperature);
    IotDevice create(CreateIotDeviceResource iotDeviceResource);
    IotDevice update(Integer iotDeviceId, UpdateIotDeviceResource request);
    ResponseEntity<?> delete(Integer iotDeviceId);
}
