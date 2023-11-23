package com.digitalholics.iotheraphy.consultation.domain.services;

import com.digitalholics.iotheraphy.consultation.domain.model.entities.AvailableHour;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.CreateAvailableHourResource;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.UpdateAvailableHourResource;
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
