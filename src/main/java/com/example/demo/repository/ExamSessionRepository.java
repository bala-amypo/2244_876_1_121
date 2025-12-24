package com.example.demo.repository;

import com.example.demo.model.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    List<ExamSession> findByExamDate(LocalDate examDate);
}