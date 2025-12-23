package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate examDate;

    @ManyToMany
    private List<Student> students;

    public ExamSession() {}

    public ExamSession(Long id, LocalDate examDate, List<Student> students) {
        this.id = id;
        this.examDate = examDate;
        this.students = students;
    }

    public Long getId() { return id; }
    public LocalDate getExamDate() { return examDate; }
    public List<Student> getStudents() { return students; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private LocalDate examDate;
        private List<Student> students;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder examDate(LocalDate examDate) { this.examDate = examDate; return this; }
        public Builder students(List<Student> students) { this.students = students; return this; }

        public ExamSession build() {
            return new ExamSession(id, examDate, students);
        }
    }
}
