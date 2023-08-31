package com.digitalholics.iotheraphy.Appointment.domain.model.entity;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Security.Domain.Model.Entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "check_requests")
public class CheckRequest {

    @Id
    @GeneratedValue
    private Integer id;

    private String therapist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    private Boolean accepted;

    private String topic;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String date;

    @Column(name = "hour")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private String hour;


    private String place;


}
