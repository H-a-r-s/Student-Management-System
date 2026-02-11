package com.harsh.Attendance_Service.repository;

import com.harsh.Attendance_Service.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    Optional<Attendance> findByStudentIdAndCourseCodeAndDate(
            Long studentId,
            String courseCode,
            LocalDate date
    );

    List<Attendance> findByStudentId(Long studentId);

    List<Attendance> findByCourseCode(String courseCode);

    List<Attendance> findByStudentIdAndCourseCode(
            Long studentId,
            String courseCode
    );
}
