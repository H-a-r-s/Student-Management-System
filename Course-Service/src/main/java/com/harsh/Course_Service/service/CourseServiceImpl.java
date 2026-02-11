package com.harsh.Course_Service.service;

import com.harsh.Course_Service.dto.CourseRequestDto;
import com.harsh.Course_Service.dto.CourseResponseDto;
import com.harsh.Course_Service.entity.Course;
import com.harsh.Course_Service.exception.CourseNotFoundException;
import com.harsh.Course_Service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    @Override
    public CourseResponseDto createCourse(CourseRequestDto request) {
        Course course = Course.builder()
                .title(request.getTitle())
                .code(request.getCode())
                .studentId(request.getStudentId())
                .build();
        courseRepository.save(course);
        return modelMapper.map(course, CourseResponseDto.class);
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(
                        () -> new CourseNotFoundException("Course not found with id: "+ id)
                );
        return modelMapper.map(course, CourseResponseDto.class);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto getCourseByCode(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() ->
                        new RuntimeException("Course not found with code: " + code)
                );

        return modelMapper.map(course, CourseResponseDto.class);
    }
}
