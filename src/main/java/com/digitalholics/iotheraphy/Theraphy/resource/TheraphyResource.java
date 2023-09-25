package com.digitalholics.iotheraphy.Theraphy.resource;

import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String startAt;

    private String finishAt;

    private PatientResource patient;
    private PhysiotherapistResource physiotherapist;
}
