package com.harsh.Attendance_Service.exception;

public class AttendanceAlreadyMarkedException extends RuntimeException{

    public AttendanceAlreadyMarkedException(String message) {
        super(message);
    }
}
