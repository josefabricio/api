package com.digitalholics.iotheraphy.consultation.interfaces.rest.resources;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PatientResource;
import com.digitalholics.iotheraphy.profile.interfaces.rest.resources.PhysiotherapistResource;
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
