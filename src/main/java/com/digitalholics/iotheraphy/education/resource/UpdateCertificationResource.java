package com.digitalholics.iotheraphy.education.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCertificationResource {
    private Integer id;
    private String title;
    private String school;
    private Integer year;
}
