package com.digitalholics.iotheraphy.Security.mapping;

import com.digitalholics.iotheraphy.Profile.mapping.PatientMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("usersMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper userMapper() { return new UserMapper();}
}
