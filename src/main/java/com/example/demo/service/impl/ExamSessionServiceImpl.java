package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import com.example.demo.model.ExamSession;

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

        // NULL checks to avoid 500 error
        if (session == null) {
            return null;
        }

        if (session.getExamDate() == null) {
            return null;
        }

        if (session.getExamDate().isBefore(LocalDate.now())) {
            return null;
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            return null;
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId).orElse(null);
    }
}
