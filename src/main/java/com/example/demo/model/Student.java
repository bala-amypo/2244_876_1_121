package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Student() {}

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters & setters

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Student build() {
            return new Student(id, name);
        }
    }
}
