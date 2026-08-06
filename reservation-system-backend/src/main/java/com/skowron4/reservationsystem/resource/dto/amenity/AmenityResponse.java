package com.skowron4.reservationsystem.resource.dto.amenity;

import java.util.UUID;

public record AmenityResponse(
        UUID publicId,
        String name,
        String icon
) {}
