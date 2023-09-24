package com.digitalholics.iotheraphy.Theraphy.resource.Treatment;

import com.digitalholics.iotheraphy.Theraphy.resource.TheraphyResource;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentResource {


    private Integer id;
    private Integer theraphyId;
    private String videoUrl;
    private String duration;
    private String title;
    private String description;
    private String day;
    private Boolean viewed;
}
