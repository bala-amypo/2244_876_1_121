package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        // Validate room to prevent 500 errors
        if (room.getRoomNumber() == null || room.getRoomNumber().isEmpty()) return null;
        if (room.getRows() <= 0 || room.getColumns() <= 0 || room.getCapacity() <= 0) return null;

        return examRoomRepository.save(room);
    }

    @Override
    public ExamRoom getRoom(Long roomId) {
        return examRoomRepository.findById(roomId).orElse(null);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
