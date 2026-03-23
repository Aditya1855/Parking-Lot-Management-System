package com.example.parkinglot_project.dtos;

import com.example.parkinglot_project.enums.VehicleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotCreateDto {
    @NotBlank
    private String parkingLotName;

    @Min(0)
    private Integer capacityTwoWheeler;

    @Min(0)
    private Integer capacityFourWheeler;

    @Min(0)
    private Double fareTwoWheeler;

    @Min(0)
    private Double fareFourWheeler;

    @NotEmpty
    private Set<VehicleType> typesAllowed;

    private Double latitude;
    private Double longitude;
    private String address;
}
