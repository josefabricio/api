package com.digitalholics.iotheraphy.Consultation.resource.AvailableHour;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AvailableHourResource {

    private Integer id;
    private Integer hours;
    private String days;
    private PhysiotherapistResource physiotherapist;
}
