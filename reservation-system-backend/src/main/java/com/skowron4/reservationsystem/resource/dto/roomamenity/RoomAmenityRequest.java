package com.skowron4.reservationsystem.resource.dto.roomamenity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RoomAmenityRequest(
        @NotNull UUID roomPublicId,
        @NotNull UUID amenityPublicId,
        @Min(1) int quantity
) {}
