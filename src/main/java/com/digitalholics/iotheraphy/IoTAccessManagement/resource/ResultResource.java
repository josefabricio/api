package com.digitalholics.iotheraphy.IoTAccessManagement.resource;

import com.digitalholics.iotheraphy.IoTAccessManagement.domain.model.entity.IotDevice;
import com.digitalholics.iotheraphy.Therapy.domain.model.entity.Treatment;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ResultResource {
    private Integer id;
    private Treatment treatment;
    private IotDevice iotDevice;
    private String date;
}
