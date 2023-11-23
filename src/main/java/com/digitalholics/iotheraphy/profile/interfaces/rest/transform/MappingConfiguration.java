package com.digitalholics.iotheraphy.profile.interfaces.rest.transform;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PatientMapper patientMapper() {
        return new PatientMapper();
    }

    @Bean
    public PhysiotherapistMapper physiotherapistMapper(){
        return new PhysiotherapistMapper();
    }
}
