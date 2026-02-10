package com.harsh.Attendance_Service.controller;

import com.harsh.Attendance_Service.entity.Attendance;
import com.harsh.Attendance_Service.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final RestTemplate restTemplate;

    private static final String STUDENT_SERVICE_URL =
            "http://localhost:8081/api/students/";

    @PostMapping
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        restTemplate.getForObject(
                STUDENT_SERVICE_URL + attendance.getStudentId(),
                Object.class
        );
        return attendanceRepository.save(attendance);
    }
}
