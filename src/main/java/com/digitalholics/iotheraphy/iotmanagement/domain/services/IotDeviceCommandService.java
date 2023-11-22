package com.digitalholics.iotheraphy.iotmanagement.domain.services;

import com.digitalholics.iotheraphy.iotmanagement.domain.model.aggregate.IotDevice;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.CreateIotDeviceResource;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.UpdateIotDeviceResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IotDeviceCommandService {
    List<IotDevice> getAll();
    Page<IotDevice> getAll(Pageable pageable);

    List<IotDevice> getByTherapyIdAndDate(Integer therapyId, String date);
    IotDevice getById(Integer iotDeviceId);
    IotDevice getByTemperature(String temperature);
    IotDevice create(CreateIotDeviceResource iotDeviceResource);
    IotDevice update(Integer iotDeviceId, UpdateIotDeviceResource request);
    ResponseEntity<?> delete(Integer iotDeviceId);
}
