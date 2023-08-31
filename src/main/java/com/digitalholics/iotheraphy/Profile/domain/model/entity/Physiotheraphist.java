package com.digitalholics.iotheraphy.Profile.domain.model.entity;

import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "physiotheraphists")
public class Physiotheraphist {
    @Id
    @GeneratedValue
    private Integer id;

    private String dni;

    private String specialization;

    private Number age;

    private String location;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "birthday_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthdayDate;

    private Double rating;

    @Column(name = "consultation_quantity")
    private Number consultationQuantity;

    @Column(name = "patient_quantity")
    private Number patientQuantity;

    @Column(name = "years_experience")
    private Number yearsExperience;

    private Double fees;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
