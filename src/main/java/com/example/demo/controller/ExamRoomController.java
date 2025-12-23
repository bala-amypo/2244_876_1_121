package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @PostMapping
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom room) {
        return ResponseEntity.status(201).body(examRoomService.addRoom(room));
    }

    // 🔥 USED BY TESTS
    public ExamRoom addRoom(ExamRoom room) {
        return examRoomService.addRoom(room);
    }

    // 🔥 USED BY TESTS
    public List<ExamRoom> list() {
        return examRoomService.getAllRooms();
    }

    // 🔥 USED BY TESTS
    public ExamRoom get(Long id) {
        return examRoomService.getById(id);
    }
}
