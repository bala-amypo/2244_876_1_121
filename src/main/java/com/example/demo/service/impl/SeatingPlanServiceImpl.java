package com.example.demo.service.impl;

import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        Optional<ExamSession> sessionOpt = examSessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return null;
        }

        ExamSession session = sessionOpt.get();
        int studentCount = session.getStudents().size();

        List<ExamRoom> rooms =
                examRoomRepository.findByCapacityGreaterThanEqual(studentCount);

        if (rooms.isEmpty()) {
            return null;
        }

        ExamRoom room = rooms.get(0);

        Map<String, String> arrangement = new LinkedHashMap<>();
        int seatNo = 1;

        for (Student student : session.getStudents()) {
            arrangement.put("Seat-" + seatNo++, student.getRollNumber());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(arrangement);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(json);

            return seatingPlanRepository.save(plan);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return seatingPlanRepository.findById(planId).orElse(null);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }
}
