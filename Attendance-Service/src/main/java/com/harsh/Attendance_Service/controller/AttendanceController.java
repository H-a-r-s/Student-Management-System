package com.harsh.Attendance_Service.controller;

import com.harsh.Attendance_Service.dto.AttendanceRequestDto;
import com.harsh.Attendance_Service.dto.AttendanceResponseDto;
import com.harsh.Attendance_Service.entity.Attendance;
import com.harsh.Attendance_Service.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponseDto> markAttendance(
            @Valid @RequestBody AttendanceRequestDto request
    ) {
        return ResponseEntity.ok(attendanceService.markAttendance(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDto>> getByStudent(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudent(studentId));
    }

    @GetMapping("/course/{courseCode}")
    public ResponseEntity<List<AttendanceResponseDto>> getByCourse(
            @PathVariable String courseCode
    ) {
        return ResponseEntity.ok(attendanceService.getAttendanceByCourse(courseCode));
    }

    @GetMapping("/student/{studentId}/course/{courseCode}")
    public ResponseEntity<List<AttendanceResponseDto>> getByStudentAndCourse(
            @PathVariable Long studentId,
            @PathVariable String courseCode
    ) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudentAndCourse(
                studentId, courseCode
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDto> updateAttendance(
            @PathVariable Long id,
            @RequestParam Boolean present
    ) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, present));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

    @GetMapping("/percentage")
    public ResponseEntity<Double> attendancePercentage(
            @RequestParam Long studentId,
            @RequestParam String courseCode
    ) {
        return ResponseEntity.ok(attendanceService.calculateAttendancePercentage(
                studentId, courseCode
        ));
    }
}
