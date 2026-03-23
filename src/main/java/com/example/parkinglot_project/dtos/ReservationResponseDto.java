package com.example.parkinglot_project.dtos;

import com.example.parkinglot_project.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private String registrationNo;
    private String parkingLotName;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Integer floor;
    private Double fare;
    private ReservationStatus status;
}
