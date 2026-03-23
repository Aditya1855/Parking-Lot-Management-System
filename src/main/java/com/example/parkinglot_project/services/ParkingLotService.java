package com.example.parkinglot_project.services;

import com.example.parkinglot_project.dtos.ParkingLotCreateDto;
import com.example.parkinglot_project.dtos.ParkingLotResponseDto;
import com.example.parkinglot_project.entities.ParkingLotEntity;
import com.example.parkinglot_project.repository.ParkingLotRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotRepository repository;
    private final ModelMapper modelMapper;

    @Transactional
    public ParkingLotResponseDto create(ParkingLotCreateDto dto) {
        ParkingLotEntity entity = modelMapper.map(dto, ParkingLotEntity.class);
        entity.setAvailableTwoWheeler(dto.getCapacityTwoWheeler());
        entity.setAvailableFourWheeler(dto.getCapacityFourWheeler());
        entity = repository.save(entity);
        return modelMapper.map(entity, ParkingLotResponseDto.class);
    }

    public ParkingLotResponseDto findById(Long id) {
        ParkingLotEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parking lot not found"));
        return modelMapper.map(entity, ParkingLotResponseDto.class);
    }

    public List<ParkingLotResponseDto> findAll() {
        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, ParkingLotResponseDto.class))
                .toList();
    }
}

