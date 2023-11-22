package com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class IotDeviceResource {
    private Integer id;
    private String temperature;
    private String distance;
    private String pulse;
    private String humidity;
    private Integer therapyId;
    private String date;
}
