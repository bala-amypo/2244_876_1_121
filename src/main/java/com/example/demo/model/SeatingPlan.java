package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String arrangementJson;

    @ManyToOne
    @JoinColumn(name = "exam_session_id")
    private ExamSession examSession;

    public SeatingPlan() {}

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public String getArrangementJson() {
        return arrangementJson;
    }

    public ExamSession getExamSession() {
        return examSession;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setArrangementJson(String arrangementJson) {
        this.arrangementJson = arrangementJson;
    }

    public void setExamSession(ExamSession examSession) {
        this.examSession = examSession;
    }
}
