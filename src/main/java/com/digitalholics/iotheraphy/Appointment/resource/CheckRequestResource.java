package com.digitalholics.iotheraphy.Appointment.resource;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CheckRequestResource {
    private Integer id;
    private Integer therapist;
    private PatientResource patient;
    private Boolean accepted;
    private String topic;
    private String date;
    private String hour;
    private String place;

}
