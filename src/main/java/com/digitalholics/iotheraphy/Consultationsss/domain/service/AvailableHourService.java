package com.digitalholics.iotheraphy.Consultationsss.domain.service;

import com.digitalholics.iotheraphy.Consultationsss.domain.model.entity.AvailableHour;
import com.digitalholics.iotheraphy.Consultationsss.resource.AvailableHour.CreateAvailableHourResource;
import com.digitalholics.iotheraphy.Consultationsss.resource.AvailableHour.UpdateAvailableHourResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AvailableHourService {
    List<AvailableHour> getAll();
    Page<AvailableHour> getAll(Pageable pageable);
    AvailableHour getById(Integer availableHourId);
    List<AvailableHour> getByPhysiotherapistId(Integer physiotherapistId);

    AvailableHour create(CreateAvailableHourResource availableHourResource);
    AvailableHour update(Integer availableHourId, UpdateAvailableHourResource request);
    ResponseEntity<?> delete(Integer availableHourId);

}
