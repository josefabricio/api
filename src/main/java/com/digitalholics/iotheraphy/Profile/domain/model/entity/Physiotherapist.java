package com.digitalholics.iotheraphy.Profile.domain.model.entity;

import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Therapy;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "physiotherapists")
public class Physiotherapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dni;

    private String specialization;

    private Integer age;

    private String location;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "birthday_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthdayDate;

    private Double rating;

    @Column(name = "consultation_quantity")
    private Integer consultationQuantity;

    @Column(name = "patient_quantity")
    private Integer patientQuantity;

    @Column(name = "years_experience")
    private Integer yearsExperience;

    private Double fees;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "physiotherapistId")
    private Set<Therapy> therapies = new HashSet<>();
}
