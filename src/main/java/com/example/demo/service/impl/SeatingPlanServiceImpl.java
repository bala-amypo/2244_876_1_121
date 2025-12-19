package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;

import org.springframework.stereotype.Service;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int studentCount = session.getStudents().size();

        ExamRoom room = examRoomRepository
                .findByCapacityGreaterThanEqual(studentCount)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        Map<String, String> arrangement = new LinkedHashMap<>();
        int seat = 1;

        for (Student s : session.getStudents()) {
            arrangement.put("Seat-" + seat++, s.getRollNumber());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(arrangement);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(json);

            return seatingPlanRepository.save(plan);

        } catch (Exception e) {
            throw new ApiException("json error");
        }
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return seatingPlanRepository.findById(planId)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}
