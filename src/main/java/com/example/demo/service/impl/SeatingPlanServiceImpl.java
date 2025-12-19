package com.example.demo.service.impl;

import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;

    public SeatingPlanServiceImpl(SeatingPlanRepository seatingPlanRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
    }

    @Override
    public List<SeatingPlan> generateSeatingPlan(Long examSessionId) {
        return seatingPlanRepository.findAllByExamSessionId(examSessionId);
    }

    @Override
    public List<SeatingPlan> getByExamSessionId(Long examSessionId) {
        return seatingPlanRepository.findAllByExamSessionId(examSessionId);
    }
}
