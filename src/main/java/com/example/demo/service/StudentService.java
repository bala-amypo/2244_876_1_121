package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student addStudent(Student student) {
        if (repo.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("exists");
        }
        return repo.save(student);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
