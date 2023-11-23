package com.digitalholics.iotheraphy.social.resource;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PatientResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResource {
    private Integer id;
    private String content;
    private Integer score;
    private PhysiotherapistResource physiotherapist;
    private PatientResource patient;
}
