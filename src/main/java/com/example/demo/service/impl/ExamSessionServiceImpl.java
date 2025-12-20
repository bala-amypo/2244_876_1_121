package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (session.getStudents() == null) {
            session.setStudents(new HashSet<>());
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId).orElse(null);
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return examSessionRepository.findAll();
    }

    @Override
    public ExamSession addStudentToSession(Long sessionId, Long studentId) {

        ExamSession session = examSessionRepository.findById(sessionId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (session == null || student == null) {
            return null;
        }

        if (session.getStudents() == null) {
            session.setStudents(new HashSet<>());
        }

        session.getStudents().add(student);
        return examSessionRepository.save(session);
    }

    
    @Override
    public Set<Student> getStudentsForSession(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId).orElse(null);

        if (session == null || session.getStudents() == null) {
            return new HashSet<>();
        }

        return session.getStudents();
    }
}
