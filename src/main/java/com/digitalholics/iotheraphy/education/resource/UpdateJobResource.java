package com.digitalholics.iotheraphy.education.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobResource {
    private Integer id;
    private Integer physiotherapistId;
    private String position;
    private String Organization;
}
