package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rollNumber;
    private String name;
    private String department;
    private Integer year;

    public Student() {}

    public Student(Long id, String rollNumber, String name, String department, Integer year) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.department = department;
        this.year = year;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    // builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String rollNumber;
        private String name;
        private String department;
        private Integer year;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder department(String department) { this.department = department; return this; }
        public Builder year(Integer year) { this.year = year; return this; }

        public Student build() {
            return new Student(id, rollNumber, name, department, year);
        }
    }
}
