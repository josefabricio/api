package com.digitalholics.iotheraphy.Therapy.resource.Therapy;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
