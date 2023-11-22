package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource.Job;

import com.digitalholics.iotheraphy.Profiless.resource.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class JobResource {
    private Integer id;
    private PhysiotherapistResource physiotherapist;
    private String position;
    private String organization;
}
