package com.digitalholics.iotheraphy.Appointment.mapping;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.CheckRequest;
import com.digitalholics.iotheraphy.Appointment.resource.CheckRequestResource;
import com.digitalholics.iotheraphy.Appointment.resource.CreateCheckRequestResource;
import com.digitalholics.iotheraphy.Appointment.resource.UpdateCheckRequestResource;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.resource.CreatePatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.UpdatePatientResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CheckRequestMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public CheckRequestResource toResource(CheckRequest model) {
        return mapper.map(model, CheckRequestResource.class);
    }

    public CheckRequest toModel(CreateCheckRequestResource resource) {
        return mapper.map(resource, CheckRequest.class);
    }

    public CheckRequest toModel(UpdateCheckRequestResource resource) { return mapper.map(resource, CheckRequest.class);}

    public Page<CheckRequestResource> modelListPage(List<CheckRequest> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CheckRequestResource.class), pageable, modelList.size());
    }


}
