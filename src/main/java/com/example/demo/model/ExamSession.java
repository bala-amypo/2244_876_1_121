package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "exam_sessions")
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;

    private LocalDate examDate;

    @ElementCollection
    @CollectionTable(name = "exam_session_students",
            joinColumns = @JoinColumn(name = "session_id"))
    @Column(name = "student_name")
    private List<String> students;

    public ExamSession() {
    }

    public ExamSession(Long id, String courseCode, LocalDate examDate, List<String> students) {
        this.id = id;
        this.courseCode = courseCode;
        this.examDate = examDate;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
