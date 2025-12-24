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

    private Integer rows;

    private Integer columns;

    private Integer capacity;

    public ExamRoom() {}

    public ExamRoom(Long id, String roomNumber, Integer rows, Integer columns, Integer capacity) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
        this.capacity = capacity;
    }

    public static ExamRoomBuilder builder() {
        return new ExamRoomBuilder();
    }

    public static class ExamRoomBuilder {
        private Long id;
        private String roomNumber;
        private Integer rows;
        private Integer columns;
        private Integer capacity;

        public ExamRoomBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ExamRoomBuilder roomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public ExamRoomBuilder rows(Integer rows) {
            this.rows = rows;
            return this;
        }

        public ExamRoomBuilder columns(Integer columns) {
            this.columns = columns;
            return this;
        }

        public ExamRoomBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public ExamRoom build() {
            ExamRoom room = new ExamRoom(id, roomNumber, rows, columns, capacity);
            room.ensureCapacityMatches();
            return room;
        }
    }

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Integer getRows() { return rows; }
    public void setRows(Integer rows) { this.rows = rows; }

    public Integer getColumns() { return columns; }
    public void setColumns(Integer columns) { this.columns = columns; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}