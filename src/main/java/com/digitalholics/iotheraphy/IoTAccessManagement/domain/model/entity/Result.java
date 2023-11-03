package com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity;

import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "treatment_id")
    @JsonIgnore
    private Treatment treatment;

    @OneToOne
    @JoinColumn(name = "iotDevice_id")
    @JsonIgnore
    private IotDevice iotDevice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String date;
}
