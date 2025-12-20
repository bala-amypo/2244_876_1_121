package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {

    private final ExamSessionService examSessionService;

    public ExamSessionController(ExamSessionService examSessionService) {
        this.examSessionService = examSessionService;
    }

   
    @PostMapping
    public ExamSession createSession(@RequestBody ExamSession session) {
        return examSessionService.createSession(session);
    }

    @GetMapping("/{id}")
    public ExamSession getSession(@PathVariable Long id) {
        return examSessionService.getSession(id);
    }

    @GetMapping
    public List<ExamSession> getAllSessions() {
        return examSessionService.getAllSessions();
    }

    
    @PostMapping("/{sessionId}/students/{studentId}")
    public ExamSession addStudentToSession(
            @PathVariable Long sessionId,
            @PathVariable Long studentId) {

        return examSessionService.addStudentToSession(sessionId, studentId);
    }

    @GetMapping("/{sessionId}/students")
    public Set<Student> getStudentsForSession(@PathVariable Long sessionId) {
        return examSessionService.getStudentsForSession(sessionId);
    }
}
