package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"students", "hibernateLazyInitializer", "handler"})
    private ExamSession examSession;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ExamRoom room;

    @Column(columnDefinition = "TEXT")
    private String arrangementJson;

    private LocalDateTime generatedAt;

    // No-arg constructor
    public SeatingPlan() {
    }

    // All-arg constructor
    public SeatingPlan(Long id, ExamSession examSession, ExamRoom room,
                       String arrangementJson, LocalDateTime generatedAt) {
        this.id = id;
        this.examSession = examSession;
        this.room = room;
        this.arrangementJson = arrangementJson;
        this.generatedAt = generatedAt;
    }

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamSession getExamSession() {
        return examSession;
    }

    public void setExamSession(ExamSession examSession) {
        this.examSession = examSession;
    }

    public ExamRoom getRoom() {
        return room;
    }

    public void setRoom(ExamRoom room) {
        this.room = room;
    }

    public String getArrangementJson() {
        return arrangementJson;
    }

    public void setArrangementJson(String arrangementJson) {
        this.arrangementJson = arrangementJson;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
