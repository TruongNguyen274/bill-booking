package vn.billbooking.service;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;
import java.util.List;

public interface PointService {

    List<Point> findAll();

    Point findById(long id);

    Point findByName(String name);

    Point save(Point point);

    List<Point> findByActive();

    Point findByLocationAndName(Location location, String name);

    List<Point> findByLocation(Location location);

}
