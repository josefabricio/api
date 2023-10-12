package com.digitalholics.iotheraphy.Therapy.resource.Treatment;

import com.digitalholics.iotheraphy.Therapy.resource.Therapy.TherapyResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentResource {

    private Integer id;
    private TherapyResource therapy;
    private String videoUrl;
    private String duration;
    private String title;
    private String description;
    private String day;
    private Boolean viewed;
}
