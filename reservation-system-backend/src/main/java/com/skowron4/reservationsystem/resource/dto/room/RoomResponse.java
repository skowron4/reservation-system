package com.skowron4.reservationsystem.resource.dto.room;

import com.skowron4.reservationsystem.resource.domain.room.RoomStatus;

import java.util.UUID;

public record RoomResponse(
        UUID publicId,
        String name,
        UUID buildingPublicId,
        int capacity,
        RoomStatus roomStatus,
        String description
) {}
