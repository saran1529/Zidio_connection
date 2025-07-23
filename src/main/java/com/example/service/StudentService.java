package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // ✅ Constructor Injection (removes warning and follows Spring best practice)
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return (student == null) ? null : mapToDTO(student);
    }

    public StudentDTO getStudentById(Long id) {
        // ✅ Replaced with functional style using map()
        return studentRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getQualification(),
                dto.getResumeURL()
        );
        Student saved = studentRepository.save(student);
        return mapToDTO(saved);
    }

    public StudentDTO updateStudent(StudentDTO dto) {
        return studentRepository.findById(dto.getId())
                .map(student -> {
                    student.setName(dto.getName());
                    student.setEmail(dto.getEmail());
                    student.setPhone(dto.getPhone());
                    student.setQualification(dto.getQualification());
                    student.setResumeURL(dto.getResumeURL());
                    return mapToDTO(studentRepository.save(student));
                })
                .orElse(null);
    }

    public boolean deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    private StudentDTO mapToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getQualification(),
                student.getResumeURL()
        );
    }
}
