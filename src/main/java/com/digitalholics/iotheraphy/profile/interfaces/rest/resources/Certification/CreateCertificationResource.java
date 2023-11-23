package com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Certification;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCertificationResource {
    private Integer id;
    private String title;
    private String school;
    private Integer year;
}
