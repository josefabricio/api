package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
