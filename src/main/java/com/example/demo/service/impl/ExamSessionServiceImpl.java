package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository) {
        this.examSessionRepository = examSessionRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("At least one student is required");
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Exam session not found"));
    }
}
