package com.digitalholics.iotheraphy.profile.domain.model.entities;


import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Patient;
import com.digitalholics.iotheraphy.profile.domain.model.aggregates.Physiotherapist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diagnoses")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "physiotherapist_id")
    @JsonIgnore
    private Physiotherapist physiotherapist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    private String diagnosis;

    @NotNull
    @NotBlank
    private String date;
}
