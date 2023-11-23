package com.digitalholics.iotheraphy.iotmanagement.domain.services;

import com.digitalholics.iotheraphy.iotmanagement.domain.model.entities.IotResult;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.CreateIotResultResource;
import com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources.UpdateIotResultResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IotDeviceQueryService {

    List<IotResult> getAll();
    Page<IotResult> getAll(Pageable pageable);
    IotResult getById(Integer resultId);
    IotResult getByTherapyId(String therapyId);
    List<IotResult> getByTherapyIdAndDate(String therapyId, String date);
    IotResult create(CreateIotResultResource resultResource);
    IotResult update(Integer resultId, UpdateIotResultResource request);
    ResponseEntity<?> delete(Integer resultId);

}
