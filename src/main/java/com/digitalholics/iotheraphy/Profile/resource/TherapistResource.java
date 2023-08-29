package com.digitalholics.iotheraphy.Profile.resource;

import com.digitalholics.iotheraphy.Security.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TherapistResource {
    private Integer id;
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
    private User user;
}
