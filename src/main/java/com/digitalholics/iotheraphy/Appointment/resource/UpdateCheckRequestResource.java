package com.digitalholics.iotheraphy.Appointment.resource;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCheckRequestResource {
    private Integer id;
    private String therapist;
    private PatientResource patient;
    private Boolean accepted;
    private String topic;
    private String date;
    private String hour;
    private String place;
}
