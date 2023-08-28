package com.digitalholics.iotheraphy.Profile.resource;

import com.digitalholics.iotheraphy.Security.User.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientResource {

    private Integer id;
    private Number age;
    private String photoUrl;
    private String birthdayDate;
    private Number appointmentQuantity;
}