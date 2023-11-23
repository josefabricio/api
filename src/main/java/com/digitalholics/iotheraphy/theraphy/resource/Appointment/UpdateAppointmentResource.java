package com.digitalholics.iotheraphy.theraphy.resource.Appointment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAppointmentResource {


    private Boolean done;

    private String topic;
    private String diagnosis;


    private String date;

    private String hour;

    private String place;
}
