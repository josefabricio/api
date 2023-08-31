package com.digitalholics.iotheraphy.Profile.resource;

import com.digitalholics.iotheraphy.Security.User.User;
import com.digitalholics.iotheraphy.Security.User.UserResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PhysiotherapistResource {
    private Integer id;
    private String dni;
    private String specialization;
    private Number age;
    private String location;
    private String photoUrl;
    private String birthdayDate;
    private Double rating;
    private Number consultationQuantity;
    private Number patientQuantity;
    private Number yearsExperience;
    private Double fees;
    private UserResource user;
}
