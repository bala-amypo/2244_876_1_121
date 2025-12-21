package com.example.demo.service;

import java.util.List;
import com.example.demo.model.ExamSession;

public interface ExamSessionService {

    ExamSession createSession(ExamSession session);

    ExamSession getSession(Long sessionId);

    List<ExamSession> getAllSessions();
}
