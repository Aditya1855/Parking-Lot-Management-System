package com.example.parkinglot_project.repository;

import com.example.parkinglot_project.entities.ParkingLotEntity;
import com.example.parkinglot_project.enums.LotStatus;
import com.example.parkinglot_project.enums.VehicleType;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, Long> {

    @Query("SELECT p FROM ParkingLotEntity p WHERE p.lotStatus = 'AVAILABLE' AND :vehicleType MEMBER OF p.typesAllowed")
    List<ParkingLotEntity> findAvailableLotsForVehicleType(@Param("vehicleType") VehicleType vehicleType);


    @Query(value = """
        SELECT * FROM parking_lots pl
        WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(pl.latitude))
        * cos(radians(pl.longitude) - radians(:lon)) + sin(radians(:lat))
        * sin(radians(pl.latitude)))) < :radiusKm
        AND pl.lot_status = 'AVAILABLE'
        """, nativeQuery = true)
    List<ParkingLotEntity> findNearbyLots(@Param("lat") double lat, @Param("lon") double lon, @Param("radiusKm") double radiusKm);
}
