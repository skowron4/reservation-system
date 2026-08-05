package com.skowron4.reservationsystem.resource.dto.building;

import java.util.UUID;

public record BuildingResponse(
        UUID publicId,
        String name,
        String address,
        String description,
        String timezone
) {}
