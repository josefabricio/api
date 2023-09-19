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

    private String location;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
