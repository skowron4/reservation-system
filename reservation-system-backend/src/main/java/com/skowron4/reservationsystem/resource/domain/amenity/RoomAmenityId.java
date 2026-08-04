package com.skowron4.reservationsystem.resource.domain.amenity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoomAmenityId implements Serializable {
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "amenity_id")
    private Integer amenityId;
}
