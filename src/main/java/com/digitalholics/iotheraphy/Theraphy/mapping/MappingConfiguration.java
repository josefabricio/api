package com.digitalholics.iotheraphy.Theraphy.mapping;


import com.digitalholics.iotheraphy.Appointment.mapping.AppointmentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("theraphiesMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public TheraphyMapper theraphyMapper() { return new TheraphyMapper();}
}
