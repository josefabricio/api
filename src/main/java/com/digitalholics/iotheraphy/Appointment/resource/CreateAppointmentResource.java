package com.digitalholics.iotheraphy.Appointment.resource;


import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentResource {

    private Integer id;
    private Boolean done;

    private String topic;
    private String diagnosis;

    private Date date;

    private Date hour;

    private String place;



}
