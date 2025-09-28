package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /** Get student by ID */
    public StudentDTO getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        return mapToDTO(optional.get());
    }

    /** Get student by email */
    public StudentDTO getStudentByEmail(String email) {
        Optional<Student> optional = studentRepository.findByEmail(email);
        if (optional.isEmpty()) {
            return null;
        }
        return mapToDTO(optional.get());
    }

    /** Create a new student */
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setPassword(passwordEncoder.encode(dto.getPassword()));

        Student saved = studentRepository.save(student);
        return mapToDTO(saved);
    }

    /** Update an existing student */
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Student student = optional.get();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            student.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        Student updated = studentRepository.save(student);
        return mapToDTO(updated);
    }

    /** Delete a student by ID */
    public boolean deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    /** Helper method to convert Student to StudentDTO */
    private StudentDTO mapToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());

        return dto;
    }
}
