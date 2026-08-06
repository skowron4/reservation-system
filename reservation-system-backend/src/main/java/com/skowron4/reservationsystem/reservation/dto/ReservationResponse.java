package com.skowron4.reservationsystem.reservation.dto;

import com.skowron4.reservationsystem.reservation.domain.ReservationStatus;
import java.time.ZonedDateTime;
import java.util.UUID;

public record ReservationResponse(
        UUID publicId,
        UUID roomPublicId,
        UUID userPublicId,
        String title,
        String description,
        int attendeesCount,
        ZonedDateTime startTime,
        ZonedDateTime endTime,
        ReservationStatus status
) {}
