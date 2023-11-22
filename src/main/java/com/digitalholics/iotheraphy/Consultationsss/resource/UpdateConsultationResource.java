package com.digitalholics.iotheraphy.Consultationsss.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateConsultationResource {
    private Boolean done;
    private String topic;
    private String diagnosis;
    private String date;
    private String hour;
    private String place;
}
