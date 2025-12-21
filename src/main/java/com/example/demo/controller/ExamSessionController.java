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
    public ResponseEntity<?> createSession(@RequestBody ExamSession session) {
        ExamSession saved = examSessionService.createSession(session);
        if (saved == null) {
            return ResponseEntity.badRequest().body("Invalid exam session data");
        }
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<?> getSession(@PathVariable Long sessionId) {
        ExamSession session = examSessionService.getSession(sessionId);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(session);
    }
}
