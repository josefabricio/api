package com.digitalholics.iotheraphy.consultation.interfaces.rest.resources;

import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AvailableHourResource {

    private Integer id;
    private String hours;
    private String day;
    private PhysiotherapistResource physiotherapist;
}
