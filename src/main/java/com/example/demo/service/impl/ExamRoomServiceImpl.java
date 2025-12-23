package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ApiException;
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
        if (room.getRows() == null || room.getColumns() == null
                || room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("rows and columns must be positive");
        }

        examRoomRepository.findByRoomNumber(room.getRoomNumber())
                .ifPresent(r -> {
                    throw new ApiException("room exists");
                });

        room.ensureCapacityMatches();
        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }

    // REQUIRED BY TESTS
    @Override
    public ExamRoom getById(Long id) {
        return examRoomRepository.findById(id)
                .orElseThrow(() -> new ApiException("room not found"));
    }
}
