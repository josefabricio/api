package com.digitalholics.iotheraphy.Therapysss.resource.Therapy;

import com.digitalholics.iotheraphy.Profiless.resource.PatientResource;
import com.digitalholics.iotheraphy.Profiless.resource.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TherapyResource {
    private Integer id;
    private String therapyName;
    private String description;
    private String appointmentQuantity;
    private String startAt;
    private String finishAt;
    private Boolean finished;
    private PatientResource patient;
    private PhysiotherapistResource physiotherapist;
}
