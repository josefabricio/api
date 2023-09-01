package com.digitalholics.iotheraphy.Profile.resource;

import com.digitalholics.iotheraphy.Security.Resource.UserResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PatientResource {
    private Integer id;
    private String dni;
    private Number age;
    private String photoUrl;
    private String birthdayDate;
    private Number appointmentQuantity;
    private UserResource user;
}
