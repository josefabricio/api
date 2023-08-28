package com.digitalholics.iotheraphy.Profile.domain.model.entity;

import com.digitalholics.iotheraphy.Security.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.transform.Source;


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

}
