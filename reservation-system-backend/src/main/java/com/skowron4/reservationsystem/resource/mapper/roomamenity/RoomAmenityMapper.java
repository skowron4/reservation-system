package com.skowron4.reservationsystem.resource.mapper.roomamenity;

import com.skowron4.reservationsystem.resource.domain.roomamenity.RoomAmenity;
import com.skowron4.reservationsystem.resource.dto.roomamenity.RoomAmenityRequest;
import com.skowron4.reservationsystem.resource.dto.roomamenity.RoomAmenityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomAmenityMapper {
    @Mapping(source = "room.publicId", target = "roomPublicId")
    @Mapping(source = "amenity.publicId", target = "amenityPublicId")
    RoomAmenityResponse toResponse(RoomAmenity roomAmenity);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "amenity", ignore = true)
    RoomAmenity toEntity(RoomAmenityRequest request);
}
