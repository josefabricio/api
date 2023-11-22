package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource;

import com.digitalholics.iotheraphy.Profiless.resource.PatientResource;
import com.digitalholics.iotheraphy.Profiless.resource.PhysiotherapistResource;
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
