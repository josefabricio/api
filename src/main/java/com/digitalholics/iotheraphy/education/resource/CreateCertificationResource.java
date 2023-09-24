package com.digitalholics.iotheraphy.education.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCertificationResource {
    private Integer id;
    private Integer physiotherapistId;
    private String title;
    private String school;
    private Integer year;
}
