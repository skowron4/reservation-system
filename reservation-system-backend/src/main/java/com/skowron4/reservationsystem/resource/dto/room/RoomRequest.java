package com.skowron4.reservationsystem.resource.dto.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RoomRequest(
        @NotNull UUID buildingPublicId,
        @NotBlank String name,
        @Min(1) int capacity,
        String description
) {}
