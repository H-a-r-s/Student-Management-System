package com.harsh.Attendance_Service.dto;
import com.harsh.Attendance_Service.entity.AttendanceStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponseDto {

    private Long id;
    private Long studentId;
    private String courseCode;
    private LocalDate date;
    @NotNull
    private AttendanceStatus status;
}
