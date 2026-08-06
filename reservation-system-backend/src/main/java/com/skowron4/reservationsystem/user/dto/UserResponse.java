package com.skowron4.reservationsystem.user.dto;

import com.skowron4.reservationsystem.user.domain.Role;
import java.util.UUID;

public record UserResponse(
        UUID publicId,
        String email,
        String name,
        String surname,
        Role role,
        boolean isActive
) {
}
