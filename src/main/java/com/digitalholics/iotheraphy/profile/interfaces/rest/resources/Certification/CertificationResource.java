package com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PhysiotherapistResource;
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
