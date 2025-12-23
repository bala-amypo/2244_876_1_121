package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ExamRoom room;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private ExamSession session;

    public SeatingPlan() {}

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Student getStudent() {
        return student;
    }

    public ExamRoom getRoom() {
        return room;
    }

    public ExamSession getSession() {
        return session;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setRoom(ExamRoom room) {
        this.room = room;
    }

    public void setSession(ExamSession session) {
        this.session = session;
    }

    // ===== BUILDER =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SeatingPlan sp = new SeatingPlan();

        public Builder id(Long id) {
            sp.setId(id);
            return this;
        }

        public Builder seatNumber(Integer seatNumber) {
            sp.setSeatNumber(seatNumber);
            return this;
        }

        public Builder student(Student student) {
            sp.setStudent(student);
            return this;
        }

        public Builder room(ExamRoom room) {
            sp.setRoom(room);
            return this;
        }

        public Builder session(ExamSession session) {
            sp.setSession(session);
            return this;
        }

        public SeatingPlan build() {
            return sp;
        }
    }
}
