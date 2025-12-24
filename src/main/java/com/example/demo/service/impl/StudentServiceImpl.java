package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        if (student.getRollNumber() == null || student.getRollNumber().isEmpty()) {
            throw new ApiException("Roll number is required");
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new ApiException("Name is required");
        }
        if (student.getYear() == null || student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("Year must be between 1 and 5");
        }
        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Student with roll number already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}