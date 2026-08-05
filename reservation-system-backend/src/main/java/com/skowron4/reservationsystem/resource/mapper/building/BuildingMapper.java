package com.skowron4.reservationsystem.resource.mapper.building;

import com.skowron4.reservationsystem.resource.domain.building.Building;
import com.skowron4.reservationsystem.resource.dto.building.BuildingRequest;
import com.skowron4.reservationsystem.resource.dto.building.BuildingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BuildingMapper {
    BuildingResponse toResponse(Building building);

    Building toEntity(BuildingRequest request);
}
