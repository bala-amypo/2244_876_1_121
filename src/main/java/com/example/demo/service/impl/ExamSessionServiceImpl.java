package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import com.example.demo.model.ExamSession;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository) {
        this.examSessionRepository = examSessionRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {
        // Validate exam session data to avoid 500
        if (session.getExamDate() == null) return null;
        if (session.getExamDate().isBefore(LocalDate.now())) return null;
        if (session.getStudents() == null || session.getStudents().isEmpty()) return null;

        // Save session only if valid
        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId).orElse(null);
    }
}
