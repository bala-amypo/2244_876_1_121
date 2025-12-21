package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository s,
                                  SeatingPlanRepository p,
                                  ExamRoomRepository r) {
        this.examSessionRepository = s;
        this.seatingPlanRepository = p;
        this.examRoomRepository = r;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        Optional<ExamSession> sessionOpt = examSessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return null;
        }

        ExamSession session = sessionOpt.get();
        Set<Student> students = session.getStudents();

        if (students == null || students.isEmpty()) {
            return null;
        }

        int studentCount = students.size();

        List<ExamRoom> rooms = examRoomRepository.findAll();
        ExamRoom selectedRoom = null;

        for (ExamRoom room : rooms) {
            if (room.getCapacity() != null && room.getCapacity() >= studentCount) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            return null;
        }

        Map<String, String> seatingMap = new LinkedHashMap<>();
        int seatIndex = 1;

        for (Student student : students) {
            seatingMap.put("Seat-" + seatIndex, student.getRollNumber());
            seatIndex++;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(selectedRoom);
            plan.setArrangementJson(mapper.writeValueAsString(seatingMap));

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
