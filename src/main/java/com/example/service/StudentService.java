package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /** ✅ Get student by ID */
    public StudentDTO getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Student student = optional.get();
        return mapToDTO(student);
    }

    /** ✅ Get student by email */
    public StudentDTO getStudentByEmail(String email) {
        Optional<Student> optional = studentRepository.findByEmail(email);
        if (optional.isEmpty()) {
            return null;
        }
        Student student = optional.get();
        return mapToDTO(student);
    }

    /** ✅ Create a new student */
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student = studentRepository.save(student);
        return mapToDTO(student);
    }

    /** ✅ Update an existing student */
    public StudentDTO updateStudent(StudentDTO dto) {
        Optional<Student> optional = studentRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            return null;
        }
        Student student = optional.get();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student = studentRepository.save(student);
        return mapToDTO(student);
    }

    /** ✅ Delete a student by ID */
    public boolean deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    /** ✅ Helper method to convert Student to StudentDTO */
    private StudentDTO mapToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        return dto;
    }
}
