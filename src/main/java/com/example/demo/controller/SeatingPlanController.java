package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seatingplans")
public class SeatingPlanController {

    private final SeatingPlanService seatingPlanService;

    public SeatingPlanController(SeatingPlanService seatingPlanService) {
        this.seatingPlanService = seatingPlanService;
    }

    @PostMapping("/{sessionId}")
    public List<SeatingPlan> generatePlan(@PathVariable Long sessionId) {
        return seatingPlanService.generateSeatingPlan(sessionId);
    }

    @GetMapping("/{sessionId}")
    public List<SeatingPlan> getPlan(@PathVariable Long sessionId) {
        return seatingPlanService.getByExamSessionId(sessionId);
    }
}
