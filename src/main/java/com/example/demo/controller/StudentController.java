package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student saved = studentService.addStudent(student);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Student>> list() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}