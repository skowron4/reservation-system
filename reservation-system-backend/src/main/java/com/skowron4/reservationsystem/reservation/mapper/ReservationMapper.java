package com.skowron4.reservationsystem.reservation.mapper;

import com.skowron4.reservationsystem.reservation.domain.Reservation;
import com.skowron4.reservationsystem.reservation.dto.ReservationRequest;
import com.skowron4.reservationsystem.reservation.dto.ReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {
    @Mapping(source = "room.publicId", target = "roomPublicId")
    @Mapping(source = "user.publicId", target = "userPublicId")
    ReservationResponse toResponse(Reservation reservation);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    Reservation toEntity(ReservationRequest request);
}
