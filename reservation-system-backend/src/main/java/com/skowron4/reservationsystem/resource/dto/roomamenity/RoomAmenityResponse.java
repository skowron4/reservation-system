package com.skowron4.reservationsystem.resource.dto.roomamenity;

import java.util.UUID;

public record RoomAmenityResponse(
        UUID roomPublicId,
        UUID amenityPublicId,
        int quantity
) {}
