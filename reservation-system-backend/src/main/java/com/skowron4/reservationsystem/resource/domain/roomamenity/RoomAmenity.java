package com.skowron4.reservationsystem.resource.domain.roomamenity;

import com.skowron4.reservationsystem.resource.domain.amenity.Amenity;
import com.skowron4.reservationsystem.resource.domain.room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

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
