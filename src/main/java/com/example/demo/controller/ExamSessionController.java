package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;

@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    private final ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

    @PostMapping
    public ResponseEntity<ExamSession> createSession(@RequestBody ExamSession session) {
        return ResponseEntity.status(201)
                .body(examSessionService.createSession(session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamSession> getSession(@PathVariable Long id) {
        return ResponseEntity.ok(examSessionService.getSession(id));
    }
}
