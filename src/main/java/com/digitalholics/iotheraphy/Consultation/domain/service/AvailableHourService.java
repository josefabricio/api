package com.digitalholics.iotheraphy.Consultation.domain.service;

import com.digitalholics.iotheraphy.Consultation.domain.model.entity.AvailableHour;
import com.digitalholics.iotheraphy.Consultation.domain.model.entity.Consultation;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.CreateAvailableHourResource;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.UpdateAvailableHourResource;
import com.digitalholics.iotheraphy.Consultation.resource.CreateConsultationResource;
import com.digitalholics.iotheraphy.Consultation.resource.UpdateConsultationResource;
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
