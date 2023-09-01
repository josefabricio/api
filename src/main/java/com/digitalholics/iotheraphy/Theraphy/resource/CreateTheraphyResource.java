package com.digitalholics.iotheraphy.Theraphy.resource;


import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import com.digitalholics.iotheraphy.Profile.domain.model.entity.Theraphist;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTheraphyResource {

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



    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "theraphist_id", nullable = false)
    private Theraphist theraphist;
}
