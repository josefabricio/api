package com.digitalholics.iotheraphy.education.resource;

import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
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
