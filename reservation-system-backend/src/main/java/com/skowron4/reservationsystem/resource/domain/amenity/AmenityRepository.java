package com.skowron4.reservationsystem.resource.domain.amenity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
    Optional<Amenity> findByPublicId(UUID publicId);

    Optional<Amenity> findByName(String name);
}
