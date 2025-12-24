package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class SeatingPlanController {

    @Autowired
    private SeatingPlanService seatingPlanService;

    public SeatingPlanController(SeatingPlanService seatingPlanService) {
        this.seatingPlanService = seatingPlanService;
    }

    @PostMapping("/generate/{sessionId}")
    public ResponseEntity<SeatingPlan> generate(@PathVariable Long sessionId) {
        SeatingPlan plan = seatingPlanService.generatePlan(sessionId);
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatingPlan> get(@PathVariable Long id) {
        SeatingPlan plan = seatingPlanService.getPlan(id);
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> list(@PathVariable Long sessionId) {
        List<SeatingPlan> plans = seatingPlanService.getPlansBySession(sessionId);
        return ResponseEntity.ok(plans);
    }
}