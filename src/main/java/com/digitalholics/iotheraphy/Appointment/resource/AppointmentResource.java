package com.digitalholics.iotheraphy.Appointment.resource;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResource {

    private Integer id;


    private boolean done;

    private String topic;
    private String diagnosis;


    private Date date;

    private Date hour;

    private String place;
}