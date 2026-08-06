package com.skowron4.reservationsystem.resource.mapper.amenity;

import com.skowron4.reservationsystem.resource.domain.amenity.Amenity;
import com.skowron4.reservationsystem.resource.dto.amenity.AmenityRequest;
import com.skowron4.reservationsystem.resource.dto.amenity.AmenityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AmenityMapper {
    AmenityResponse toResponse(Amenity amenity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Amenity toEntity(AmenityRequest request);
}
