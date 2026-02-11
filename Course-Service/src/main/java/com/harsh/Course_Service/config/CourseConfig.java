package com.harsh.Course_Service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
