package com.harsh.Course_Service.repository;

import com.harsh.Course_Service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {

//    List<Course> findByStudentId(Long studentId);
Optional<Course> findByCode(String code);

}
