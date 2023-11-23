package com.digitalholics.iotheraphy.profile.interfaces.rest.resources;

import com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhysiotherapistResource {
    private String dni;
    private String specialization;
    private Integer age;
    private String location;
    private String photoUrl;
    private String birthdayDate;
    private Double rating;
    private Integer consultationQuantity;
    private Integer patientQuantity;
    private Integer yearsExperience;
    private Double fees;
    private User user;
}
