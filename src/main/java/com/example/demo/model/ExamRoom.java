package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ExamRoom {

    @Id
    @GeneratedValue
    private Long id;

    private String roomName;
    private int capacity;

    public ExamRoom() {}

    public ExamRoom(Long id, String roomName, int capacity) {
        this.id = id;
        this.roomName = roomName;
        this.capacity = capacity;
    }

    // getters & setters

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String roomName;
        private int capacity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder roomName(String roomName) {
            this.roomName = roomName;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public ExamRoom build() {
            return new ExamRoom(id, roomName, capacity);
        }
    }
}
