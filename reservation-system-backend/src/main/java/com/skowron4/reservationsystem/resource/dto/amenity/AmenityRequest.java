package com.skowron4.reservationsystem.resource.dto.amenity;

import jakarta.validation.constraints.NotBlank;

public record AmenityRequest(
        @NotBlank String name,
        String icon
) {}
