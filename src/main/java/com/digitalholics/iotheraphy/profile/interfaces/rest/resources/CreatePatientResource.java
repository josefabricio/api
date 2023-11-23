package com.digitalholics.iotheraphy.profile.interfaces.rest.resources;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResource {

    private Integer id;
    private String dni;
    private Integer age;
    private String photoUrl;
    private String birthdayDate;
    private Integer appointmentQuantity;
    private String location;
}