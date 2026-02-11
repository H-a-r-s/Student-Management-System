package com.harsh.Attendance_Service.service;

import com.harsh.Attendance_Service.dto.AttendanceRequestDto;
import com.harsh.Attendance_Service.dto.AttendanceResponseDto;

import java.util.List;

public interface AttendanceService {
    AttendanceResponseDto markAttendance(AttendanceRequestDto request);

    List<AttendanceResponseDto> getAttendanceByStudent(Long studentId);

    List<AttendanceResponseDto> getAttendanceByCourse(String courseCode);

    List<AttendanceResponseDto> getAttendanceByStudentAndCourse(
            Long studentId,
            String courseCode
    );

    AttendanceResponseDto updateAttendance(
            Long attendanceId,
            Boolean present
    );

    void deleteAttendance(Long attendanceId);

    Double calculateAttendancePercentage(
            Long studentId,
            String courseCode
    );
}
