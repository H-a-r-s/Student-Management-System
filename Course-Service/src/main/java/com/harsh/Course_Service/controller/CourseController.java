package com.harsh.Course_Service.controller;

import com.harsh.Course_Service.entity.Course;
import com.harsh.Course_Service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<Course> getCoursesByStudent(@PathVariable Long studentId) {
        return courseRepository.findByStudentId(studentId);
    }
}
