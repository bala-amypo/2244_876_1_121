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

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ExamRoom room;

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

    public ExamRoom getRoom() {
        return room;
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

    public void setRoom(ExamRoom room) {
        this.room = room;
    }
}
