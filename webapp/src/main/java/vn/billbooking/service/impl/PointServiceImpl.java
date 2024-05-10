package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;
import vn.billbooking.repository.PointRepository;
import vn.billbooking.service.PointService;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointRepository pointRepository;

    @Override
    public List<Point> findAll() {
        return pointRepository.findAll();
    }

    @Override
    public Point findById(long id) {
        return pointRepository.findById(id).orElse(null);
    }

    @Override
    public Point findByName(String name) {
        return pointRepository.findByName(name).orElse(null);
    }

    @Override
    public Point save(Point point) {
        return pointRepository.save(point);
    }

    @Override
    public List<Point> findByActive() {
        return pointRepository.findByStatusIsTrue();
    }

    @Override
    public Point findByLocationAndName(Location location, String name) {
        return pointRepository.findByLocationAndName(location, name).orElse(null);
    }

    @Override
    public List<Point> findByLocation(Location location) {
        return pointRepository.findByLocation(location);
    }

}
