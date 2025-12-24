package com.example.demo.repository;

import com.example.demo.model.SeatingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {

    List<SeatingPlan> findByExamSessionId(Long examSessionId);
}