package com.digitalholics.iotheraphy.Theraphy.resource;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Theraphist;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TheraphyResource {


    private Integer id;


    private String theraphyName;

    private String appointmentQuantity;


    private String appointmentGap;

    private Date startAt;

    private Date finishAt;


    private Patient patient;

    private Theraphist theraphist;
}
