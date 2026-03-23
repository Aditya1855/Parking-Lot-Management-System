package com.example.parkinglot_project.controllers;

import com.example.parkinglot_project.dtos.ReservationRequestDto;
import com.example.parkinglot_project.dtos.ReservationResponseDto;
import com.example.parkinglot_project.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDto create(@Valid @RequestBody ReservationRequestDto dto) {
        return service.create(dto);
    }
}
