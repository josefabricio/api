package com.digitalholics.iotheraphy.IoTAccessManagement.resource;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class IotResultResource {
    private Integer id;
    private String iotDeviceId;
    private String humidity;
    private String temperature;
    private String pulse;
    private String mapAmplitude;
    private String mapFrequency;
    private String mapDuration;
    private String date;
}
