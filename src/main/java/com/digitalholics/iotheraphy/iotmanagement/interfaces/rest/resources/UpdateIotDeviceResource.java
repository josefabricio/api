package com.digitalholics.iotheraphy.iotmanagement.interfaces.rest.resources;

import lombok.*;

@Getter
@Setter
public class UpdateIotDeviceResource {
    private String temperature;
    private String distance;
    private String pulse;
    private String humidity;
    private Integer therapyId;
    private String date;

}
