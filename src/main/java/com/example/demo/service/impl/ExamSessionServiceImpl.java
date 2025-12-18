package com.example.demo.service.impl;

import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repository;

    public ExamSessionServiceImpl(ExamSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExamSession save(ExamSession session) {
        return repository.save(session);
    }

    @Override
    public List<ExamSession> getAll() {
        return repository.findAll();
    }

    @Override
    public ExamSession getSession(Long id) {
        return repository.findById(id).orElse(null);
    }
}
