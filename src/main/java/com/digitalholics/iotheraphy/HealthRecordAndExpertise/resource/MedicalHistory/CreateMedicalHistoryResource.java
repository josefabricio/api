package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.MedicalHistory;

import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicalHistoryResource {
    private Integer id;
    private String gender;
    private Double size;
    private Double weight;
    private String birthplace;
    private String hereditaryHistory;
    private String nonPathologicalHistory;
    private String pathologicalHistory;
}
