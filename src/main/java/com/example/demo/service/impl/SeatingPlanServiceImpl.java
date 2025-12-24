package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    @Autowired
    private ExamSessionRepository examSessionRepository;

    @Autowired
    private SeatingPlanRepository seatingPlanRepository;

    @Autowired
    private ExamRoomRepository examRoomRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository, SeatingPlanRepository seatingPlanRepository, ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        List<ExamRoom> rooms = examRoomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new ApiException("No room available");
        }

        ExamRoom selectedRoom = rooms.stream()
                .filter(r -> r.getCapacity() >= session.getStudents().size())
                .min(Comparator.comparing(ExamRoom::getCapacity))
                .orElseThrow(() -> new ApiException("No suitable room"));

        // Simple arrangement: list of roll numbers
        List<String> rollNumbers = session.getStudents().stream()
                .map(s -> s.getRollNumber())
                .collect(Collectors.toList());

        String arrangementJson;
        try {
            arrangementJson = objectMapper.writeValueAsString(Map.of("seats", rollNumbers));
        } catch (JsonProcessingException e) {
            arrangementJson = "{}";
        }

        SeatingPlan plan = SeatingPlan.builder()
                .examSession(session)
                .room(selectedRoom)
                .arrangementJson(arrangementJson)
                .generatedAt(LocalDateTime.now())
                .build();

        return seatingPlanRepository.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return seatingPlanRepository.findById(id)
                .orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}