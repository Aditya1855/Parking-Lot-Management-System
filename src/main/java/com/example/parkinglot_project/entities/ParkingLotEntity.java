package com.example.parkinglot_project.entities;

import com.example.parkinglot_project.enums.LotStatus;
import com.example.parkinglot_project.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "parking_lots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String parkingLotName;

    @Column(nullable = false)
    private Integer capacityTwoWheeler;

    @Column(nullable = false)
    private Integer capacityFourWheeler;

    @Column(nullable = false)
    private Double fareTwoWheeler;

    @Column(nullable = false)
    private Double fareFourWheeler;

    @ElementCollection(targetClass = VehicleType.class)
    @CollectionTable(name = "lot_allowed_types", joinColumns = @JoinColumn(name = "lot_id"))
    @Enumerated(EnumType.STRING)
    private Set<VehicleType> typesAllowed = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private LotStatus lotStatus = LotStatus.AVAILABLE;

    private Integer availableTwoWheeler;
    private Integer availableFourWheeler;

    private Double latitude;
    private Double longitude;

    @Column(length = 500)
    private String address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationEntity> reservations = new ArrayList<>();
}
