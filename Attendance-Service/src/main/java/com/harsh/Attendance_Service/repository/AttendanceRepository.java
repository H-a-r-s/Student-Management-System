package com.harsh.Attendance_Service.repository;

import com.harsh.Attendance_Service.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
}
