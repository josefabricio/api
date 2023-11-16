package com.digitalholics.iotheraphy.IoTAccessManagement.domain.service;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotResult;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.CreateIotResultResource;
import com.digitalholics.iotheraphy.IoTAccessManagement.resource.UpdateIotResultResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IotResultService {

    List<IotResult> getAll();
    Page<IotResult> getAll(Pageable pageable);
    IotResult getById(Integer resultId);
    IotResult getByTherapyId(String therapyId);
    List<IotResult> getByTherapyIdAndDate(String therapyId, String date);
    IotResult create(CreateIotResultResource resultResource);
    IotResult update(Integer resultId, UpdateIotResultResource request);
    ResponseEntity<?> delete(Integer resultId);

}
