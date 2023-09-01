package com.digitalholics.iotheraphy.Theraphy.domain.model.entity;

import com.digitalholics.iotheraphy.Appointment.domain.model.entity.Appointment;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "theraphies")
public class Theraphy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @NotBlank
    @Column(name = "theraphy_name")
    @Size(max = 300)
    private String theraphyName;

    @NotNull
    @NotBlank
    @Column(name = "appointment_quantity")
    @Size(max = 300)
    private String appointmentQuantity;

    @NotNull
    @NotBlank
    @Column(name = "appointment_gap")
    @Size(max = 300)
    private String appointmentGap;

    @NotNull
    @NotBlank
    @Column(name = "start_at")
    private Date startAt;

    @NotNull
    @NotBlank
    @Column(name = "finish_at")
    private Date finishAt;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "theraphy")
    private Set<Appointment> appointments = new HashSet<>();



    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;






}
