package com.digitalholics.iotheraphy.consultation.interfaces.rest.resources;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAvailableHourResource {

    private Integer id;
    private String hours;
    private String day;
    private Integer physiotherapistId;
}
