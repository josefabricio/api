package com.digitalholics.iotheraphy.IoTAccessManagement.resource;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateResultResource {
    private Integer id;
    private Integer treatmentId;
    private Integer iotDeviceId;
    private String date;
}
