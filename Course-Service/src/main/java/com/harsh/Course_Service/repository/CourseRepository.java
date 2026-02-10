package com.harsh.Course_Service.repository;

import com.harsh.Course_Service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByStudentId(Long studentId);
}
