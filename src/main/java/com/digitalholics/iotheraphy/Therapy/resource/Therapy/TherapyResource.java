package com.digitalholics.iotheraphy.Therapy.resource.Therapy;

import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TherapyResource {


    private Integer id;


    private String therapyName;

    private String appointmentQuantity;

    private String startAt;

    private String finishAt;
    private Boolean finished;
    private PatientResource patient;
    private PhysiotherapistResource physiotherapist;
}
