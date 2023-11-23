package com.digitalholics.iotheraphy.profile.interfaces.rest.resources.Job;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobResource {
    private Integer id;
    private String position;
    private String organization;
}
