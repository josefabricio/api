package com.digitalholics.iotheraphy.education.domain.model.entity;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Physiotheraphist;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Physiotheraphist physiotherapist;

    @Size(max = 50)
    private String position;

    @Size(max = 50)
    private String Organization;

}

