package com.skowron4.reservationsystem.resource.domain.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BuildingScheduleRepository extends JpaRepository<BuildingSchedule, Integer> {
    Optional<BuildingSchedule> findByPublicId(UUID publicId);

    List<BuildingSchedule> findByBuildingId(Integer buildingId);
}
