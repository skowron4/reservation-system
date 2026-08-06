package com.skowron4.reservationsystem.user.mapper;

import com.skowron4.reservationsystem.user.domain.User;
import com.skowron4.reservationsystem.user.dto.UserRegistrationRequest;
import com.skowron4.reservationsystem.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponse toResponse(User user);

    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserRegistrationRequest request);
}
