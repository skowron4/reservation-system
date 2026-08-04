package com.skowron4.reservationsystem.resource.domain.amenity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "amenities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "public_id", nullable = false, updatable = false, unique = true)
    @Builder.Default
    private UUID publicId = UUID.randomUUID();

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String icon;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
}
