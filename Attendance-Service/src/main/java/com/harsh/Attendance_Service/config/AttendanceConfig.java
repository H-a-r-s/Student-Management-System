package com.harsh.Attendance_Service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AttendanceConfig {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
