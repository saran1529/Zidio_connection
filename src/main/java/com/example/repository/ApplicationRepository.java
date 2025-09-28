package com.example.repository;

import com.example.entity.Application;
import com.example.entity.Student;
import com.example.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {


    List<Application> findByStudent(Student student);

    List<Application> findByJobPost(JobPost jobPost);

    List<Application> findByStudentAndJobPost(Student student, JobPost jobPost);
}
