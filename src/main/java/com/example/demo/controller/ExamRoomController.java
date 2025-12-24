package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class ExamRoomController {

    @Autowired
    private ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @PostMapping
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom room) {
        ExamRoom saved = examRoomService.addRoom(room);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ExamRoom>> list() {
        List<ExamRoom> rooms = examRoomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}