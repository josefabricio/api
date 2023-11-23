package com.digitalholics.iotheraphy.theraphy.resource.Therapy;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PatientResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PhysiotherapistResource;
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
