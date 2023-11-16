package com.digitalholics.iotheraphy.Social.resource;

import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
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
