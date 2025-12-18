package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examsessions")
public class ExamSessionController {

    private final ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

    @PostMapping
    public ExamSession createSession(@RequestBody ExamSession session) {
        return examSessionService.save(session);
    }

    @GetMapping
    public List<ExamSession> getAllSessions() {
        return examSessionService.getAll();
    }
}
