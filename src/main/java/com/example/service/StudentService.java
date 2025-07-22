package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        if(student == null) return null;
        return mapToDTO(student);
    }

    public  StudentDTO getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) return null;
        return  mapToDTO(studentOptional.get());}

    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getQualification(),
                dto.getResumeURL());
        Student saved = studentRepository.save(student);
        return mapToDTO(saved);}

    public  StudentDTO updateStudent(StudentDTO dto) {
        Optional<Student> studentOptional = studentRepository.findById(dto.getId());
        if (studentOptional.isEmpty()) return  null;

        Student student = studentOptional.get();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setQualification(dto.getQualification());
        student.setResumeURL(dto.getResumeURL());

        Student updated = studentRepository.save(student);
        return mapToDTO(updated);
    }

    public  boolean deleteStudent(Long id) {
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
