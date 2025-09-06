package com.example.controller;

import com.example.DTO.StudentDTO;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing student data.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    /**
     * Get student by email.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        StudentDTO dto = studentService.getStudentByEmail(email);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /**
     * Get student by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO dto = studentService.getStudentById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /**
     * Create a new student.
     */
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO dto) {
        StudentDTO created = studentService.createStudent(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Count total number of students.
     */
    @GetMapping("/internal/count")
    public ResponseEntity<Long> countStudents() {
        return ResponseEntity.ok(studentRepository.count());
    }

    /**
     * Update an existing student.
     */
    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.updateStudent(dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    /**
     * Delete a student by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        return !deleted ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }
}
