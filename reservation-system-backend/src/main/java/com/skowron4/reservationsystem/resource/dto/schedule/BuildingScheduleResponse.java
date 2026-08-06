package com.skowron4.reservationsystem.resource.dto.schedule;

import java.time.LocalTime;
import java.util.UUID;

public record BuildingScheduleResponse(
        UUID publicId,
        UUID buildingPublicId,
        Integer dayOfWeek,
        LocalTime openTime,
        LocalTime closeTime
) {}
