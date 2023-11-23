package com.digitalholics.iotheraphy.theraphy.resource.Treatment;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTreatmentResource {

    private Integer id;
    private Integer therapyId;
    private String videoUrl;
    private String duration;
    private String title;
    private String description;
    private String day;
    private Boolean viewed;
}
