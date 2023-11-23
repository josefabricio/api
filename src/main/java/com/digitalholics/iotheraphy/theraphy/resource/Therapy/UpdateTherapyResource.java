package com.digitalholics.iotheraphy.theraphy.resource.Therapy;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTherapyResource {


    private String therapyName;
    private String description;
    private String appointmentQuantity;

    private String startAt;

    private String finishAt;
    private Boolean finished;
}
