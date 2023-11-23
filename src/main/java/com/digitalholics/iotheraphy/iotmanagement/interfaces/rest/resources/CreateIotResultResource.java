package com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateIotResultResource {
    private Integer id;
    private String iotDeviceId;
    private String humidity;
    private String temperature;
    private String pulse;
    private String mapAmplitude;
    private String mapFrequency;
    private String mapDuration;
}
