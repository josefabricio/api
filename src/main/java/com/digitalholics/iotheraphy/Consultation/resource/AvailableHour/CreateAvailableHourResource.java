package com.digitalholics.iotheraphy.Consultation.resource.AvailableHour;

import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAvailableHourResource {

    private Integer id;
    private Integer hours;
    private String days;
    private Integer physiotherapistId;
}
