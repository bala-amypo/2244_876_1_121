package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_rooms")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    @Column(name = "room_rows")
    private Integer rows;

    @Column(name = "room_columns")
    private Integer columns;

    private Integer capacity;

    // No-arg constructor (required by JPA)
    public ExamRoom() {
    }

    // Parameterized constructor
    public ExamRoom(String roomNumber, Integer rows, Integer columns) {
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
        ensureCapacityMatches();
    }

    // Business logic
    public void ensureCapacityMatches() {
        if (this.rows != null && this.columns != null) {
            this.capacity = this.rows * this.columns;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRows() {
        return rows;
    }
    
    public void setRows(Integer rows) {
        this.rows = rows;
        ensureCapacityMatches();
    }

    public Integer getColumns() {
        return columns;
    }
    
    public void setColumns(Integer columns) {
        this.columns = columns;
        ensureCapacityMatches();
    }

    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
