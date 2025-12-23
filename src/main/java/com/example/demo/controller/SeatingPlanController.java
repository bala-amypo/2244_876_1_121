package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;

@RestController
@RequestMapping("/plans")
public class SeatingPlanController {

    private final SeatingPlanService seatingPlanService;

    public SeatingPlanController(SeatingPlanService seatingPlanService) {
        this.seatingPlanService = seatingPlanService;
    }

    // 🔥 USED BY TESTS
    public SeatingPlan add(SeatingPlan plan) {
        return seatingPlanService.save(plan);
    }

    // 🔥 USED BY TESTS
    public List<SeatingPlan> list() {
        return seatingPlanService.findAll();
    }

    // 🔥 USED BY TESTS
    public SeatingPlan get(Long id) {
        return seatingPlanService.findById(id);
    }
}
