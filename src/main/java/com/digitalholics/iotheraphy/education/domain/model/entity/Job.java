package com.digitalholics.iotheraphy.education.domain.model.entity;

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
    @GeneratedValue
    private Integer id;

    @Column(name = "physiotherapist_id")
    private Integer physiotherapistId;

    @Size(max = 50)
    private String position;

    @Size(max = 50)
    private String Organization;

}

