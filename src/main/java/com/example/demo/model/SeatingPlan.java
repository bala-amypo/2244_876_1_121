package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime generatedAt;

    @Column(length = 2000)
    private String arrangementJson;

    @ManyToOne
    private ExamSession examSession;

    @ManyToOne
    private ExamRoom room;

    public SeatingPlan() {
        this.generatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public String getArrangementJson() {
        return arrangementJson;
    }

    public ExamSession getExamSession() {
        return examSession;
    }

    public ExamRoom getRoom() {
        return room;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setArrangementJson(String arrangementJson) {
        this.arrangementJson = arrangementJson;
    }

    public void setExamSession(ExamSession examSession) {
        this.examSession = examSession;
    }

    public void setRoom(ExamRoom room) {
        this.room = room;
    }
}
