package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repo;
    private final StudentRepository studentRepo;

    public ExamSessionServiceImpl(ExamSessionRepository repo,
                                  StudentRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

   @Override
public ExamSession createSession(ExamSession session) {

    // 🚫 Never allow ID on POST
    session.setId(null);

    if (session.getExamDate().isBefore(LocalDate.now())) {
        throw new ApiException("Past date not allowed");
    }

    if (session.getStudents() == null || session.getStudents().isEmpty()) {
        throw new ApiException("At least 1 student required");
    }

    // 🚫 Students must be NEW here
    for (Student s : session.getStudents()) {
        s.setId(null);
    }

    return repo.save(session);
}



    @Override
    public ExamSession getSession(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }
}
