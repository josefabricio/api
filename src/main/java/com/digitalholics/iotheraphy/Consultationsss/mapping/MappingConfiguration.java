package com.digitalholics.iotheraphy.Consultationsss.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("consultationsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ConsultationMapper consultationMapper() { return new ConsultationMapper();}

    @Bean
    public AvailableHourMapper availableHourMapper() { return new AvailableHourMapper();}
}
