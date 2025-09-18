package com.example.controller;

import com.example.DTO.StudentDTO;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing student data.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    // ✅ Constructor injection (best practice)
    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    /**
     * ✅ Get student by email
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        StudentDTO dto = studentService.getStudentByEmail(email);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /**
     * ✅ Get student by ID
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO dto = studentService.getStudentById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /**
     * ✅ Create a new student
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO dto) {

        try {
            if (dto.getName() == null || dto.getEmail() == null || dto.getPassword() == null) {
                return ResponseEntity.badRequest().body(
                        new ErrorResponse("Name, email, and password are required")
                );
            }

            StudentDTO created = studentService.createStudent(dto);
            return ResponseEntity.ok(created);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(
                    new ErrorResponse(ex.getMessage())
            );
        }
    }

    /**
     * ✅ Update an existing student
     */
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        StudentDTO updated = studentService.updateStudent(id, dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    /**
     * ✅ Delete a student by ID
     */
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        return !deleted ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    /**
     * ✅ Count total number of students (used internally)
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/internal/count")
    public ResponseEntity<Long> countStudents() {
        long count = studentRepository.count();
        return ResponseEntity.ok(count);
    }

    // Inner class for consistent error responses
    public static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
