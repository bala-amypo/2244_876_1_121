package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomService(ExamRoomRepository repo) {
        this.repo = repo;
    }

    public ExamRoom addRoom(ExamRoom room) {
        if (repo.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("exists");
        }
        return repo.save(room);
    }
}
