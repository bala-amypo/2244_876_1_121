package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository,
                                  StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {
        if (session.getExamDate() == null || session.getExamDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Exam date cannot be null or in the past.");
        }
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new IllegalArgumentException("Exam session must have at least one student.");
        }
        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        Optional<ExamSession> session = examSessionRepository.findById(sessionId);
        return session.orElseThrow(() -> new IllegalArgumentException("Session not found with id: " + sessionId));
    }
}
