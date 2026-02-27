package com.akshay.dcrs.repository;

import com.akshay.dcrs.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Long> {

    Optional<Ward> findByLatitudeMinLessThanEqualAndLatitudeMaxGreaterThanEqualAndLongitudeMinLessThanEqualAndLongitudeMaxGreaterThanEqual(
            Double latitude,
            Double latitude2,
            Double longitude,
            Double longitude2
    );
}