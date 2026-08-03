package com.skowron4.reservationsystem.resource.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByPublicId(UUID publicId);

    List<Room> findByBuildingId(Integer buildingId);
}
