package com.digitalholics.iotheraphy.Profile.domain.model.entity;

import com.digitalholics.iotheraphy.Security.User.User;
import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.transform.Source;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {


    @Id
    @GeneratedValue
    private Integer id;

    private String dni;

    private Number age;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "birthday_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthdayDate;

    private Number appointmentQuantity;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "patient")
    private Set<Theraphy> theraphies = new HashSet<>();


    private Patient addTheraphy(Integer physiotherapistId,String theraphyName, String appointmentQuantity, String appointmentGap, Date startAt, Date finishAt){

        theraphies.add(new Theraphy()
                .withTheraphyName(theraphyName)
                .withFinishAt(finishAt)
                .withAppointmentQuantity(appointmentQuantity)
                .withStartAt(startAt)
                .withAppointmentGap(appointmentGap)
                .withPatient(this));

        return this;
    }


}
