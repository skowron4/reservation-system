package com.skowron4.reservationsystem.resource.mapper.schedule;

import com.skowron4.reservationsystem.resource.domain.schedule.BuildingSchedule;
import com.skowron4.reservationsystem.resource.dto.schedule.BuildingScheduleRequest;
import com.skowron4.reservationsystem.resource.dto.schedule.BuildingScheduleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BuildingScheduleMapper {
    @Mapping(source = "building.publicId", target = "buildingPublicId")
    BuildingScheduleResponse toResponse(BuildingSchedule buildingSchedule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "building", ignore = true)
    BuildingSchedule toEntity(BuildingScheduleRequest request);
}
