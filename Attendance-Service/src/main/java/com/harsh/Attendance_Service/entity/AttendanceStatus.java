package com.harsh.Attendance_Service.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AttendanceStatus {
    PRESENT,
    ABSENT;

    @JsonCreator
    public static AttendanceStatus from(String value) {
        return AttendanceStatus.valueOf(value.toUpperCase());
    }
}

