package com.goat.controller;

import com.goat.model.Position;
import com.goat.repository.PositionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionRepository positionRepository;

    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping
    public List<Position> getAll() {
        return positionRepository.findAll();
    }
}