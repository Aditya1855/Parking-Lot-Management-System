package com.example.parkinglot_project.dtos;

import com.example.parkinglot_project.enums.LotStatus;
import com.example.parkinglot_project.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotResponseDto {
    private Long id;
    private String parkingLotName;
    private Integer capacityTwoWheeler;
    private Integer capacityFourWheeler;
    private Double fareTwoWheeler;
    private Double fareFourWheeler;
    private Set<VehicleType> typesAllowed;
    private LotStatus lotStatus;
    private Integer availableTwoWheeler;
    private Integer availableFourWheeler;
    private Double latitude;
    private Double longitude;
    private String address;
}
