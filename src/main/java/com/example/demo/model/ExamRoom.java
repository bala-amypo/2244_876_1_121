package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private Integer rows;
    private Integer columns;
    private Integer capacity;

    public ExamRoom() {}

    public ExamRoom(Long id, String roomNumber, Integer rows, Integer columns) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
        ensureCapacityMatches();
    }

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }

    public Long getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public Integer getRows() { return rows; }
    public Integer getColumns() { return columns; }
    public Integer getCapacity() { return capacity; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String roomNumber;
        private Integer rows;
        private Integer columns;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder roomNumber(String roomNumber) { this.roomNumber = roomNumber; return this; }
        public Builder rows(Integer rows) { this.rows = rows; return this; }
        public Builder columns(Integer columns) { this.columns = columns; return this; }

        public ExamRoom build() {
            return new ExamRoom(id, roomNumber, rows, columns);
        }
    }
}
