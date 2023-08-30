package com.digitalholics.iotheraphy.Profile.resource;

import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResource {

    private Integer id;
    private String dni;
    private Number age;
    private String photoUrl;
    private String birthdayDate;
    private Number appointmentQuantity;

}