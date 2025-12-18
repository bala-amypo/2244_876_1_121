package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long examSessionId;
    private String roomNumber;

    // Default constructor
    public SeatingPlan() {
    }

    // Parameterized constructor
    public SeatingPlan(Long examSessionId, String roomNumber) {
        this.examSessionId = examSessionId;
        this.roomNumber = roomNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamSessionId() {
        return examSessionId;
    }

    public void setExamSessionId(Long examSessionId) {
        this.examSessionId = examSessionId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
