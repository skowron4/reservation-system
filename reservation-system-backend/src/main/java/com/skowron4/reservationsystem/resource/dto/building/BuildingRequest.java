package com.skowron4.reservationsystem.resource.dto.building;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BuildingRequest(
        @NotBlank String name,
        @NotBlank String address,
        String description,
        String timezone
) {
}
