package com.digitalholics.iotheraphy.Appointment.mapping;

import com.digitalholics.iotheraphy.Profile.mapping.PatientMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AppointmentsMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public CheckRequestMapper checkRequestMapper() { return new CheckRequestMapper();}
}