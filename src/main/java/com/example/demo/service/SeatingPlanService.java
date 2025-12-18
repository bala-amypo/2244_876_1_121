package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

@Service
public class SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanService(ExamSessionRepository s,
                              SeatingPlanRepository p,
                              ExamRoomRepository r) {
        this.sessionRepo = s;
        this.planRepo = p;
        this.roomRepo = r;
    }

    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int count = session.getStudents().size();

        ExamRoom room = roomRepo.findByCapacityGreaterThanEqual(count)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSessionId(sessionId);
        plan.setRoomNumber(room.getRoomNumber());

        return planRepo.save(plan);
    }
}
