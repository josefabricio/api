package com.digitalholics.iotheraphy.Consultation.mapping;

import com.digitalholics.iotheraphy.Consultation.domain.model.entity.AvailableHour;
import com.digitalholics.iotheraphy.Consultation.domain.model.entity.Consultation;
import com.digitalholics.iotheraphy.Consultation.resource.AvailableHour.AvailableHourResource;
import com.digitalholics.iotheraphy.Consultation.resource.ConsultationResource;
import com.digitalholics.iotheraphy.Consultation.resource.CreateConsultationResource;
import com.digitalholics.iotheraphy.Consultation.resource.UpdateConsultationResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AvailableHourMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AvailableHourResource toResource(AvailableHour model) {
        return mapper.map(model, AvailableHourResource.class);
    }

    public AvailableHour toModel(CreateConsultationResource resource) {
        return mapper.map(resource, AvailableHour.class);
    }

    public AvailableHour toModel(UpdateConsultationResource resource) {
        return mapper.map(resource, AvailableHour.class);
    }

    public Page<AvailableHourResource> modelListPage(List<AvailableHour> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, AvailableHourResource.class), pageable, modelList.size());
    }
}
