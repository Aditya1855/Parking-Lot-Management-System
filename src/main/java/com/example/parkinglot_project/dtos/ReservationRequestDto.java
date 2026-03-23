package com.example.parkinglot_project.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    @NotNull
    private Long vehicleId;
    @NotNull
    private Long parkingLotId;
    private Integer preferredFloor;
}