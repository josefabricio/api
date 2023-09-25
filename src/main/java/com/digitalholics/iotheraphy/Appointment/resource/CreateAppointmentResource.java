package com.digitalholics.iotheraphy.Appointment.resource;


import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.digitalholics.iotheraphy.Theraphy.resource.TheraphyResource;
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

    private String date;

    private String hour;

    private String place;

    private Integer theraphyId;

}
