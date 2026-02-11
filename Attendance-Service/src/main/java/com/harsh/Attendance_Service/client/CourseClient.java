package com.harsh.Attendance_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "course-service",
        url = "http://localhost:8082"
)
public interface CourseClient {

    @GetMapping("/api/courses/code/{code}")
    void getCourseByCode(@PathVariable String code);
}
