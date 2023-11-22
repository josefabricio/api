package com.digitalholics.iotheraphy.consultation.interfaces.rest.transform;

import com.digitalholics.iotheraphy.consultation.domain.model.entities.AvailableHour;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.AvailableHourResource;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.CreateAvailableHourResource;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.resources.UpdateAvailableHourResource;
import com.digitalholics.iotheraphy.shared.EnhancedModelMapper;
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

    public AvailableHour toModel(CreateAvailableHourResource resource) {
        return mapper.map(resource, AvailableHour.class);
    }

    public AvailableHour toModel(UpdateAvailableHourResource resource) {
        return mapper.map(resource, AvailableHour.class);
    }

    public Page<AvailableHourResource> modelListPage(List<AvailableHour> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, AvailableHourResource.class), pageable, modelList.size());
    }
}
