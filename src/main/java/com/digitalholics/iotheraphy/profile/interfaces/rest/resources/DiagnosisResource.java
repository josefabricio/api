package com.digitalholics.iotheraphy.profile.interfaces.rest.resources;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisResource {

    private Integer id;
    private PhysiotherapistResource physiotherapist;
    private PatientResource patient;
    private String diagnosis;
    private String date;
}
