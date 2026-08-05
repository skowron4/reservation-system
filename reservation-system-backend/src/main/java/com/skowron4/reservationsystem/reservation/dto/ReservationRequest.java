package com.skowron4.reservationsystem.reservation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

public record ReservationRequest(
        @NotNull UUID roomPublicId,
        @NotBlank String title,
        String description,
        @Min(1) int attendeesCount,
        @NotNull @Future ZonedDateTime startTime,
        @NotNull @Future ZonedDateTime endTime
) {
    public ReservationRequest {
        if (startTime != null && endTime != null && !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }
}
