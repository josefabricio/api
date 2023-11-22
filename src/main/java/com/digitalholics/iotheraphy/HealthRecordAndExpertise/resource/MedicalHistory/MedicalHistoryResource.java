package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory;

import com.digitalholics.iotheraphy.Profiless.resource.PatientResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryResource {

    private Integer id;
    private PatientResource patient;
    private String gender;
    private Double size;
    private Double weight;
    private String birthplace;
    private String hereditaryHistory;
    private String nonPathologicalHistory;
    private String pathologicalHistory;
}
