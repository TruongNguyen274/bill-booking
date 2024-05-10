package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Optional<Point> findByName(String name);

    List<Point> findByStatusIsTrue();

    Optional<Point> findByLocationAndName(Location location, String name);

    List<Point> findByLocation(Location location);

}