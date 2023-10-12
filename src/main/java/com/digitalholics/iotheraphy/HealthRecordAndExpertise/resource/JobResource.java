package com.digitalholics.iotheraphy.HealthRecordAndExpertise.resource;

import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
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
    private String Organization;
}
