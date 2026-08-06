package com.skowron4.reservationsystem.resource.dto.schedule;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.UUID;

public record BuildingScheduleRequest(
        @NotNull UUID buildingPublicId,
        @NotNull @Min(0) @Max(6) int dayOfWeek,
        @NotNull LocalTime openTime,
        @NotNull LocalTime closeTime
) {}
