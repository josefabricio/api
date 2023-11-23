package com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PhysiotherapistResource;
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
