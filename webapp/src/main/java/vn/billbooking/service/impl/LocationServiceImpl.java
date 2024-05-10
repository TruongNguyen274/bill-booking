package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Location;
import vn.billbooking.repository.LocationRepository;
import vn.billbooking.service.LocationService;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location findByName(String name) {
        return locationRepository.findByName(name).orElse(null);
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> findByActive() {
        return locationRepository.findByStatusIsTrue();
    }

}
