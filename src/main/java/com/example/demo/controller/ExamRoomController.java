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
    public ResponseEntity<ExamRoom> addRoom(@RequestBody ExamRoom room) {
        return ResponseEntity.status(201).body(examRoomService.addRoom(room));
    }

    @GetMapping
    public ResponseEntity<List<ExamRoom>> getAllRooms() {
        return ResponseEntity.ok(examRoomService.getAllRooms());
    }

    // REQUIRED BY TESTS
    @GetMapping("/{id}")
    public ResponseEntity<ExamRoom> get(@PathVariable Long id) {
        return ResponseEntity.ok(examRoomService.getById(id));
    }

    // REQUIRED BY TESTS
    @GetMapping("/list")
    public ResponseEntity<List<ExamRoom>> list() {
        return ResponseEntity.ok(examRoomService.getAllRooms());
    }
}
