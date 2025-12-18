package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ExamSessionService {

    private final ExamSessionRepository repo;

    public ExamSessionService(ExamSessionRepository repo) {
        this.repo = repo;
    }

    public ExamSession addSession(ExamSession session) {
        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("past");
        }
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("at least 1 student");
        }
        return repo.save(session);
    }
}
