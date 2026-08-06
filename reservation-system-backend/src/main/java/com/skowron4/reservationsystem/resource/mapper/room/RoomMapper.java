package com.skowron4.reservationsystem.resource.mapper.room;

import com.skowron4.reservationsystem.resource.domain.room.Room;
import com.skowron4.reservationsystem.resource.dto.room.RoomRequest;
import com.skowron4.reservationsystem.resource.dto.room.RoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {
    @Mapping(source = "building.publicId", target = "buildingPublicId")
    RoomResponse toResponse(Room room);

    @Mapping(target = "building", ignore = true)
    @Mapping(target = "status", ignore = true)
    Room toEntity(RoomRequest request);
}
