package com.harsh.Course_Service.controller;

import com.harsh.Course_Service.dto.CourseRequestDto;
import com.harsh.Course_Service.dto.CourseResponseDto;
import com.harsh.Course_Service.entity.Course;
import com.harsh.Course_Service.repository.CourseRepository;
import com.harsh.Course_Service.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(course));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CourseResponseDto> getCourseByCode(
            @PathVariable String code
    ) {
        return ResponseEntity.ok(courseService.getCourseByCode(code));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<CourseResponseDto> getCoursesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.getCourseById(studentId));
    }
}
