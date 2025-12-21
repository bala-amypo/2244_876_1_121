package com.example.demo.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository s,
                                  SeatingPlanRepository p,
                                  ExamRoomRepository r) {
        this.examSessionRepository = s;
        this.seatingPlanRepository = p;
        this.examRoomRepository = r;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        Optional<ExamSession> opt = examSessionRepository.findById(sessionId);
        if (opt.isEmpty()) return null;

        ExamSession session = opt.get();
        Set<Student> students = session.getStudents();
        if (students == null || students.isEmpty()) return null;

        int count = students.size();
        ExamRoom room = examRoomRepository.findAll().stream()
                .filter(r -> r.getCapacity() != null && r.getCapacity() >= count)
                .findFirst().orElse(null);

        if (room == null) return null;

        Map<String, String> map = new LinkedHashMap<>();
        int i = 1;
        for (Student s : students) map.put("Seat-" + i++, s.getRollNumber());

        try {
            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(new ObjectMapper().writeValueAsString(map));
            return seatingPlanRepository.save(plan);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return seatingPlanRepository.findById(id).orElse(null);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}
