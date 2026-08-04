package com.skowron4.reservationsystem.reservation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByPublicId(UUID publicId);

    List<Reservation> findByUserId(Integer userId);

    List<Reservation> findByRoomId(Integer roomId);
}
