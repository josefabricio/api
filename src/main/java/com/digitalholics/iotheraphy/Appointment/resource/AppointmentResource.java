package com.digitalholics.iotheraphy.Appointment.resource;


import com.digitalholics.iotheraphy.Theraphy.resource.TheraphyResource;
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


    private Boolean done;

    private String topic;
    private String diagnosis;


    private String date;

    private String hour;

    private String place;

    private TheraphyResource theraphy;
}
