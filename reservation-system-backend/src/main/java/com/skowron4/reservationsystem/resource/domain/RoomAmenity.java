package com.skowron4.reservationsystem.resource.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Table(name = "room_amenities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoomAmenity {
    @EmbeddedId
    @EqualsAndHashCode.Include
    @Builder.Default
    private RoomAmenityId id = new RoomAmenityId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("amenityId")
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    @Column(nullable = false)
    @Min(1)
    @Builder.Default
    private Integer quantity = 1;
}
