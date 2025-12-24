package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    @Autowired
    private ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        if (room.getRoomNumber() == null || room.getRoomNumber().isEmpty()) {
            throw new ApiException("Room number is required");
        }
        if (room.getRows() == null || room.getRows() <= 0) {
            throw new ApiException("Rows must be positive");
        }
        if (room.getColumns() == null || room.getColumns() <= 0) {
            throw new ApiException("Columns must be positive");
        }
        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room with room number already exists");
        }
        room.ensureCapacityMatches();
        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}