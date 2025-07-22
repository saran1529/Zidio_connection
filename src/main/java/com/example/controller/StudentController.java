package com.example.controller;

import com.example.DTO.StudentDTO;
import com.example.repository.ApplicationRepository;
import com.example.repository.JobPostRepository;
import com.example.repository.RecruiterRepository;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;



    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(
            @PathVariable String email) {
        StudentDTO dto = studentService.getStudentByEmail(email);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDTO> getStudentById(
            @PathVariable Long id) {
        StudentDTO dto = studentService.getStudentById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(
            @RequestBody StudentDTO dto) {
        StudentDTO created = studentService.createStudent(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("internal/count")
    public  ResponseEntity<Long>countStudents() {
        return ResponseEntity.ok(studentRepository.count());
    }


    @PutMapping("/update")
    public ResponseEntity<StudentDTO> updateStudent(
            @RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.updateStudent(dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(updated);
    }



    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
