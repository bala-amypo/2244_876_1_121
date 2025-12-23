package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ExamSession {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate examDate;

    public ExamSession() {}

    public ExamSession(Long id, LocalDate examDate) {
        this.id = id;
        this.examDate = examDate;
    }

    // getters & setters

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private LocalDate examDate;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder examDate(LocalDate examDate) {
            this.examDate = examDate;
            return this;
        }

        public ExamSession build() {
            return new ExamSession(id, examDate);
        }
    }
}
