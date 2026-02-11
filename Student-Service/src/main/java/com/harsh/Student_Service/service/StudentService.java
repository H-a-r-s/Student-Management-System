package com.harsh.Student_Service.service;

import com.harsh.Student_Service.dto.StudentRequestDto;
import com.harsh.Student_Service.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto request);

    StudentResponseDto getStudentById(Long id);

    List<StudentResponseDto> getAllStudents();
}
