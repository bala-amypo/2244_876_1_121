package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {

    @Autowired
    private ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

    @PostMapping
    public ResponseEntity<ExamSession> create(@RequestBody ExamSession session) {
        ExamSession saved = examSessionService.createSession(session);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamSession> get(@PathVariable Long id) {
        ExamSession session = examSessionService.getSession(id);
        return ResponseEntity.ok(session);
    }
}