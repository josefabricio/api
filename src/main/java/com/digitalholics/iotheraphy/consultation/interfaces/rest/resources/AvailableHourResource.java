package com.digitalholics.iotheraphy.consultation.interfaces.rest.resources;

import com.digitalholics.iotheraphy.Profiless.resource.PhysiotherapistResource;
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
