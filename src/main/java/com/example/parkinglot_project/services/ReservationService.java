package com.example.parkinglot_project.services;

import com.example.parkinglot_project.dtos.ReservationRequestDto;
import com.example.parkinglot_project.dtos.ReservationResponseDto;
import com.example.parkinglot_project.entities.ParkingLotEntity;
import com.example.parkinglot_project.entities.ReservationEntity;
import com.example.parkinglot_project.entities.VehicleEntity;
import com.example.parkinglot_project.enums.ReservationStatus;
import com.example.parkinglot_project.enums.VehicleType;
import com.example.parkinglot_project.repository.ParkingLotRepository;
import com.example.parkinglot_project.repository.ReservationRepository;
import com.example.parkinglot_project.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final ParkingLotRepository lotRepo;
    private final VehicleRepository vehicleRepo;
    private final ModelMapper modelMapper;

    @Transactional
    public ReservationResponseDto create(ReservationRequestDto dto) {
        VehicleEntity vehicle = vehicleRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        ParkingLotEntity lot = lotRepo.findById(dto.getParkingLotId())
                .orElseThrow(() -> new EntityNotFoundException("Lot not found"));

        if (!lot.getTypesAllowed().contains(vehicle.getVehicleType())) {
            throw new IllegalStateException("Vehicle type not allowed here");
        }

        int available = vehicle.getVehicleType() == VehicleType.TWO_WHEELER ?
                lot.getAvailableTwoWheeler() : lot.getAvailableFourWheeler();

        if (available <= 0) {
            throw new IllegalStateException("No slots available");
        }

        ReservationEntity reservation = ReservationEntity.builder()
                .vehicle(vehicle)
                .parkingLot(lot)
                .entryTime(LocalDateTime.now())
                .floor(dto.getPreferredFloor())
                .status(ReservationStatus.PARKED)
                .build();

        // Update slots
        if (vehicle.getVehicleType() == VehicleType.TWO_WHEELER) {
            lot.setAvailableTwoWheeler(lot.getAvailableTwoWheeler() - 1);
        } else {
            lot.setAvailableFourWheeler(lot.getAvailableFourWheeler() - 1);
        }

        reservation = reservationRepo.save(reservation);
        lotRepo.save(lot);

        // Simple fare calculation example
        double rate = vehicle.getVehicleType() == VehicleType.TWO_WHEELER ?
                lot.getFareTwoWheeler() : lot.getFareFourWheeler();
        reservation.setFare(rate * 2); // assume 2 hours for demo

        return buildResponse(reservation);
    }

    private ReservationResponseDto buildResponse(ReservationEntity res) {
        ReservationResponseDto dto = modelMapper.map(res, ReservationResponseDto.class);
        dto.setRegistrationNo(res.getVehicle().getRegistrationNo());
        dto.setParkingLotName(res.getParkingLot().getParkingLotName());
        return dto;
    }
}
