package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Certification;

import com.digitalholics.iotheraphy.Profiless.resource.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CertificationResource {
    private Integer id;
    private PhysiotherapistResource physiotherapist;
    private String title;
    private String school;
    private Integer year;

}
