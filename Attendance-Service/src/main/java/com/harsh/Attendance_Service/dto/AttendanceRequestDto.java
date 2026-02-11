package com.harsh.Attendance_Service.dto;

import com.harsh.Attendance_Service.entity.AttendanceStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequestDto {

    @NotNull
    private Long studentId;

    @NotNull
    private String courseCode;

    @NotNull
    private LocalDate date;

    @NotNull
//    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
}
