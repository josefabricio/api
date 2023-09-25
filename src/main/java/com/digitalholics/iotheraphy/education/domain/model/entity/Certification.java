package com.digitalholics.iotheraphy.education.domain.model.entity;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "physiotherapist_id")
    @JsonIgnore
    private Physiotheraphist physiotheraphist;

    @Size(max = 50)
    private String title;

    @Size(max = 50)
    private String school;

    private Integer year;



}
