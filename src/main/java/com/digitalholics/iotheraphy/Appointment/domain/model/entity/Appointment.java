package com.digitalholics.iotheraphy.Appointment.domain.model.entity;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Theraphist;
import com.digitalholics.iotheraphy.Theraphy.domain.model.entity.Theraphy;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private Boolean done;

    @NotNull
    @NotBlank
    private String topic;

    @NotNull
    @NotBlank
    private String diagnosis;

    @NotNull
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @NotBlank
    private Date hour;

    @NotNull
    @NotBlank
    private String place;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "theraphy_id", nullable = false)
    private Theraphy theraphy;



}
