package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final SeatingPlanRepository seatingPlanRepository;

    public SeatingPlanServiceImpl(SeatingPlanRepository seatingPlanRepository) {
        this.seatingPlanRepository = seatingPlanRepository;
    }

    @Override
    public SeatingPlan save(SeatingPlan plan) {
        return seatingPlanRepository.save(plan);
    }

    @Override
    public List<SeatingPlan> findAll() {
        return seatingPlanRepository.findAll();
    }

    @Override
    public SeatingPlan findById(Long id) {
        return seatingPlanRepository.findById(id)
                .orElseThrow(() -> new ApiException("seating plan not found"));
    }
}
