package com.digitalholics.iotheraphy.Therapysss.mapping;

import com.digitalholics.iotheraphy.Therapysss.domain.model.entity.Appointment;
import com.digitalholics.iotheraphy.Therapysss.resource.Appointment.AppointmentResource;
import com.digitalholics.iotheraphy.Therapysss.resource.Appointment.CreateAppointmentResource;
import com.digitalholics.iotheraphy.Therapysss.resource.Appointment.UpdateAppointmentResource;
import com.digitalholics.iotheraphy.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;


public class AppointmentMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public AppointmentResource toResource(Appointment model){
        return mapper.map(model, AppointmentResource.class);
    }

    public Appointment toModel(CreateAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }

    public Appointment toModel(UpdateAppointmentResource resource) { return mapper.map(resource, Appointment.class);}

    public Page<AppointmentResource> modelListPage(List<Appointment> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, AppointmentResource.class), pageable, modelList.size());
    }




}
