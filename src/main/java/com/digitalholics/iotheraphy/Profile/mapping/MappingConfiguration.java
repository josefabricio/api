package com.digitalholics.iotheraphy.Profile.mapping;

import com.digitalholics.iotheraphy.Profile.domain.model.entity.Patient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("patientsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PatientMapper appointmentMapper() { return new PatientMapper();}
}
