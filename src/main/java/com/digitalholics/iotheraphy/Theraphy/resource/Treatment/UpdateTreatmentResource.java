package com.digitalholics.iotheraphy.Theraphy.resource.Treatment;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class UpdateTreatmentResource {

    private Integer id;

    private String videoUrl;


    private String duration;


    private String title;


    private String description;


    private String day;



    private Boolean viewed;

}
