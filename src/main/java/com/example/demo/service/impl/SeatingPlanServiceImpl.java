package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamSessionRepository examSessionRepository;

    public SeatingPlanServiceImpl(SeatingPlanRepository seatingPlanRepository,
                                  ExamSessionRepository examSessionRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
        this.examSessionRepository = examSessionRepository;
    }

    @Override
    public SeatingPlan createSeatingPlan(Long sessionId) {

        ExamSession session = examSessionRepository
                .findById(sessionId)
                .orElse(null);

        if (session == null) {
            return null;
        }

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);

        return seatingPlanRepository.save(plan);
    }
}
