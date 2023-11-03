package com.digitalholics.iotheraphy.Consultation.resource;
import com.digitalholics.iotheraphy.Profile.resource.PatientResource;
import com.digitalholics.iotheraphy.Profile.resource.PhysiotherapistResource;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationResource {

    private Integer id;
    private Boolean done;
    private String topic;
    private String diagnosis;
    private String date;
    private String hour;
    private String place;
    private PhysiotherapistResource physiotherapist;
    private PatientResource patient;
}
