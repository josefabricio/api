package com.digitalholics.iotheraphy.IoTAccessManagement.domain.service;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.Result;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateIotDeviceResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateResultResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateIotDeviceResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateResultResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResultService {

    List<Result> getAll();
    Page<Result> getAll(Pageable pageable);
    Result getById(Integer resultId);
    Result getByTreatmentId(Integer treatmentId);
    Result create(CreateResultResource resultResource);
    Result update(Integer resultId, UpdateResultResource request);
    ResponseEntity<?> delete(Integer resultId);

}
