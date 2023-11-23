package com.digitalholics.iotheraphy.theraphy.resource.Therapy;


import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTherapyResource {

    private Integer id;
    private String therapyName;
    private String description;
    private String appointmentQuantity;
    private String startAt;
    private String finishAt;
    private Boolean finished;
    private Integer patientId;
}
