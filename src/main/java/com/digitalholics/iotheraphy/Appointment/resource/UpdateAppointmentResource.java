package com.digitalholics.iotheraphy.Appointment.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateAppointmentResource {


    private Integer id;


    private Boolean done;

    private String topic;
    private String diagnosis;


    private String date;

    private String hour;

    private String place;
}
