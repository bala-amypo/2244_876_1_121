package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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

        if (session == null) {
            return null;
        }

        if (session.getExamDate() == null ||
                session.getExamDate().isBefore(LocalDate.now())) {
            return null;
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            return null;
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {

        Optional<ExamSession> sessionOpt =
                examSessionRepository.findById(sessionId);

        if (sessionOpt.isEmpty()) {
            return null;
        }

        return sessionOpt.get();
    }
}
