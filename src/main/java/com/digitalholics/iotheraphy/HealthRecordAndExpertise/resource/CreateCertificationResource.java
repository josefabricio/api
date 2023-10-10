package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCertificationResource {
    private Integer id;
    private String title;
    private String school;
    private Integer year;
}