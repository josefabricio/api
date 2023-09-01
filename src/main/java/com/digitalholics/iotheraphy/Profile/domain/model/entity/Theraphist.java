package com.digitalholics.iotheraphy.Profile.domain.model.entity;

import com.digitalholics.iotheraphy.Profile.domain.service.PatientService;
import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theraphists")
public class Theraphist {
    @Id
    @GeneratedValue
    private Integer id;

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


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "theraphistId")
    private Set<Theraphy> theraphies = new HashSet<>();

    private Theraphist addTheraphy(String theraphyName, String appointmentQuantity, String appointmentGap, Date startAt, Date finishAt){

        theraphies.add(new Theraphy()
                .withTheraphyName(theraphyName)
                .withFinishAt(finishAt)
                .withAppointmentQuantity(appointmentQuantity)
                .withStartAt(startAt)
                .withAppointmentGap(appointmentGap)
             //   .withPatient( patientId)
                .withTheraphistId(this));

        return this;
    }




}
