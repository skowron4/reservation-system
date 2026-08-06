package com.skowron4.reservationsystem.resource.dto.building;

import com.skowron4.reservationsystem.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public record BuildingResponse(
        UUID publicId,
        String name,
        String address,
        String description,
        String timezone,
        List<UserResponse> managers
) {}
