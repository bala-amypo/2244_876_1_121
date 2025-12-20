package com.example.demo.service;

import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Set;

public interface ExamSessionService {

    ExamSession createSession(ExamSession session);

    ExamSession getSession(Long sessionId);

    List<ExamSession> getAllSessions();

    ExamSession addStudentToSession(Long sessionId, Long studentId);

    Set<Student> getStudentsForSession(Long sessionId);
}
