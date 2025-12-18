package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examrooms")
public class ExamRoomController {

    private final ExamRoomService examRoomService;

    public ExamRoomController(ExamRoomService examRoomService) {
        this.examRoomService = examRoomService;
    }

    @PostMapping
    public ExamRoom createRoom(@RequestBody ExamRoom room) {
        return examRoomService.save(room);
    }

    @GetMapping
    public List<ExamRoom> getAllRooms() {
        return examRoomService.getAll();
    }
}
