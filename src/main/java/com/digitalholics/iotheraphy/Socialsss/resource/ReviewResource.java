package com.digitalholics.iotheraphy.Socialsss.resource;

import com.digitalholics.iotheraphy.Profiless.resource.PatientResource;
import com.digitalholics.iotheraphy.Profiless.resource.PhysiotherapistResource;
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
