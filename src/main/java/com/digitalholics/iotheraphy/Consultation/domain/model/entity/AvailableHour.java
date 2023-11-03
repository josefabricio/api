package com.digitalholics.iotheraphy.Consultation.domain.model.entity;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotherapist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_hours")
public class AvailableHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hours;

    private String day;

    @ManyToOne
    @JoinColumn(name = "physiotherapist_id")
    @JsonIgnore
    private Physiotherapist physiotherapist;

}
