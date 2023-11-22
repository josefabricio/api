package com.digitalholics.iotheraphy.consultation.interfaces.rest.transform;

import com.digitalholics.iotheraphy.consultation.interfaces.rest.transform.AvailableHourMapper;
import com.digitalholics.iotheraphy.consultation.interfaces.rest.transform.ConsultationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("consultationsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ConsultationMapper consultationMapper() { return new ConsultationMapper();}

    @Bean
    public AvailableHourMapper availableHourMapper() { return new AvailableHourMapper();}
}
