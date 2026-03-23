package com.example.parkinglot_project.controllers;

import com.example.parkinglot_project.dtos.ParkingLotCreateDto;
import com.example.parkinglot_project.dtos.ParkingLotResponseDto;
import com.example.parkinglot_project.services.ParkingLotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-lots")
@RequiredArgsConstructor
public class ParkingLotController {

    private final ParkingLotService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotResponseDto create(@Valid @RequestBody ParkingLotCreateDto dto) {
        System.out.println(dto);
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public ParkingLotResponseDto getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ParkingLotResponseDto> getAll() {
        return service.findAll();
    }
}