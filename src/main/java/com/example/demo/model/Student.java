package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rollNumber;
    private Integer year;

    public Student() {}

    public Student(Long id, String rollNumber, Integer year) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.year = year;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String rollNumber;
        private Integer year;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        public Builder year(Integer year) { this.year = year; return this; }

        public Student build() {
            return new Student(id, rollNumber, year);
        }
    }
}
