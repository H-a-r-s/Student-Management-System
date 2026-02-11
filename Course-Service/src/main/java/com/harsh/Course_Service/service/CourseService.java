package com.harsh.Course_Service.service;

import com.harsh.Course_Service.dto.CourseRequestDto;
import com.harsh.Course_Service.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto request);

    CourseResponseDto getCourseById(Long id);

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto getCourseByCode(String code);

}
