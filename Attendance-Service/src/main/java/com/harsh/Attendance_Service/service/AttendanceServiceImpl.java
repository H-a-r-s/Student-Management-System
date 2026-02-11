package com.harsh.Attendance_Service.service;

import com.harsh.Attendance_Service.client.CourseClient;
import com.harsh.Attendance_Service.client.StudentClient;
import com.harsh.Attendance_Service.dto.AttendanceRequestDto;
import com.harsh.Attendance_Service.dto.AttendanceResponseDto;
import com.harsh.Attendance_Service.entity.Attendance;
import com.harsh.Attendance_Service.entity.AttendanceStatus;
import com.harsh.Attendance_Service.exception.AttendanceAlreadyMarkedException;
import com.harsh.Attendance_Service.exception.ResourceNotFoundException;
import com.harsh.Attendance_Service.repository.AttendanceRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{

    private final AttendanceRepository attendanceRepository;
    private final ModelMapper modelMapper;
    private final StudentClient studentClient;
    private final CourseClient courseClient;
    @Override
    public AttendanceResponseDto markAttendance(AttendanceRequestDto request) {


        try {
            studentClient.getStudentById(request.getStudentId());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Student not found: " + request.getCourseCode());
        }
        try {
            courseClient.getCourseByCode(request.getCourseCode());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Course not found: " + request.getCourseCode());
        }

        Attendance attendance = attendanceRepository
                .findByStudentIdAndCourseCodeAndDate(
                        request.getStudentId(),
                        request.getCourseCode(),
                        request.getDate()
                )
                .map(existing -> {
                    // Update existing record
                    existing.setStatus(request.getStatus());
                    return existing;
                })
                .orElseGet(() -> Attendance.builder()
                        .studentId(request.getStudentId())
                        .courseCode(request.getCourseCode())
                        .date(request.getDate())
                        .status(request.getStatus())
                        .build()
                );
        attendanceRepository.save(attendance);
        return modelMapper.map(attendance, AttendanceResponseDto.class);
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map( attendance -> modelMapper.map(attendance, AttendanceResponseDto.class))
                .toList();
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceByCourse(String courseCode) {
        return attendanceRepository.findByCourseCode(courseCode)
                .stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceResponseDto.class))
                .toList();
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceByStudentAndCourse(Long studentId, String courseCode) {
        return attendanceRepository
                .findByStudentIdAndCourseCode(studentId, courseCode)
                .stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceResponseDto.class))
                .toList();
    }

    @Override
    public AttendanceResponseDto updateAttendance(Long attendanceId, Boolean status) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found")
                );

        attendance.setStatus(
                status ? AttendanceStatus.PRESENT : AttendanceStatus.ABSENT
        );
        Attendance updated = attendanceRepository.save(attendance);

        return modelMapper.map(attendance, AttendanceResponseDto.class);
    }

    @Override
    public void deleteAttendance(Long attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new ResourceNotFoundException("Attendance not found");
        }
        attendanceRepository.deleteById(attendanceId);

    }

    @Override
    public Double calculateAttendancePercentage(Long studentId, String courseCode) {
        List<Attendance> records =
                attendanceRepository.findByStudentIdAndCourseCode(
                        studentId, courseCode
                );

        if (records.isEmpty()) return 0.0;

        long presentCount = records.stream()
                .filter(record -> record.getStatus() == AttendanceStatus.PRESENT)
                .count();

        return (presentCount * 100.0) / records.size();
    }
}
