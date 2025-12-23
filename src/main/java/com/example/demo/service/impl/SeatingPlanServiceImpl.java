package com.example.demo.service.impl;

import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamSessionRepository examSessionRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(SeatingPlanRepository seatingPlanRepository,
                                  ExamSessionRepository examSessionRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
        this.examSessionRepository = examSessionRepository;
        this.examRoomRepository = examRoomRepository;
    }

    // ================= REQUIRED BY INTERFACE =================

    @Override
    public SeatingPlan generatePlan(Long examSessionId) {
        ExamSession session = examSessionRepository.findById(examSessionId)
                .orElseThrow(() -> new RuntimeException("ExamSession not found"));

        List<ExamRoom> rooms = examRoomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new RuntimeException("No rooms available");
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(rooms.get(0));
        plan.setArrangementJson("{}");
        plan.setGeneratedAt(LocalDateTime.now());

        return seatingPlanRepository.save(plan);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long examSessionId) {
        return seatingPlanRepository.findByExamSessionId(examSessionId);
    }

    @Override
    public SeatingPlan getById(Long id) {
        return seatingPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SeatingPlan not found"));
    }
}
