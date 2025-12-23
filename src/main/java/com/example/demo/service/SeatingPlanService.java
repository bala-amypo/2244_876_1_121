package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SeatingPlan;

public interface SeatingPlanService {

    SeatingPlan save(SeatingPlan plan);

    List<SeatingPlan> findAll();

    SeatingPlan findById(Long id);
}
