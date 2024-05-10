package vn.billbooking.service;

import vn.billbooking.model.entity.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location findById(long id);

    Location findByName(String name);

    Location save(Location location);

    List<Location> findByActive();

}
