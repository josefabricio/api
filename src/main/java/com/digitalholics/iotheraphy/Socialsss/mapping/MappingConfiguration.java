package com.digitalholics.iotheraphy.Socialsss.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("socialMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ReviewMapper reviewMapper() { return new ReviewMapper();}
}
