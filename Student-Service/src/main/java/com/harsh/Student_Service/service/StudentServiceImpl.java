package com.harsh.Student_Service.service;

import com.harsh.Student_Service.dto.StudentRequestDto;
import com.harsh.Student_Service.dto.StudentResponseDto;
import com.harsh.Student_Service.entity.Student;
import com.harsh.Student_Service.exception.ResourceNotFoundException;
import com.harsh.Student_Service.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public StudentResponseDto createStudent(StudentRequestDto request) {
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .build();
        studentRepository.save(student);
        return modelMapper.map(student,StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student,StudentResponseDto.class);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student-> modelMapper.map(student,StudentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existing.setName(requestDto.getName());
        existing.setEmail(requestDto.getEmail());
        existing.setDepartment(requestDto.getDepartment());

        Student saved = studentRepository.save(existing);
        return modelMapper.map(saved, StudentResponseDto.class);
    }


    @Override
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

}
