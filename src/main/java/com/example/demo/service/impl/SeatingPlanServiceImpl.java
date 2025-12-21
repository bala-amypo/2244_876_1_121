package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;

@Service
public class SeatingPlanServiceImpl {

    private final ExamSessionRepository examSessionRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository) {
        this.examSessionRepository = examSessionRepository;
    }

    public List<String> generateSeatingPlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        List<String> students = session.getStudents();

        if (students == null || students.isEmpty()) {
            throw new ApiException("No students available for seating plan");
        }

        List<String> seatingPlan = new ArrayList<>();

        int seatNo = 1;
        for (String studentName : students) {
            seatingPlan.add("Seat " + seatNo + " : " + studentName);
            seatNo++;
        }

        return seatingPlan;
    }
}
