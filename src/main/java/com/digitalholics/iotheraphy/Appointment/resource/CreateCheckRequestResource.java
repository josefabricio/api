package com.digitalholics.iotheraphy.Appointment.resource;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCheckRequestResource {

    private Integer id;
    private String therapist;
    private PatientResource patient;
    private Boolean accepted;
    private String topic;
    private String date;
    private String hour;
    private String place;

}


