package com.example.repository;

import com.example.entity.Application;
import com.example.entity.Student;
import com.example.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Find all applications by a specific student
    List<Application> findByStudent(Student student);

    // Find all applications for a specific job post
    List<Application> findByJobPost(JobPost jobPost);

    // Find applications by student and job post
    List<Application> findByStudentAndJobPost(Student student, JobPost jobPost);
}
