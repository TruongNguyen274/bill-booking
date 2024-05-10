package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.dto.PointDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;
import vn.billbooking.model.mapper.PointMapper;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PointService;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointMapperImpl implements PointMapper {

    @Autowired
    private PointService pointService;

    @Autowired
    private LocationService locationService;

    @Override
    public PointDTO toDTO(Point point) {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setId(point.getId());
        pointDTO.setName(point.getName());
        pointDTO.setDescription(point.getDescription());
        pointDTO.setStatus(point.isStatus());

        LocationDTO locationDTO =  new LocationDTO();
        Location location =  point.getLocation();
        if (location != null){
            locationDTO.setId(location.getId());
            locationDTO.setName(location.getName());
        }
        pointDTO.setLocation(locationDTO);
        pointDTO.setLocationId(location.getId());

        return pointDTO;
    }

    @Override
    public List<PointDTO> toListDTO(List<Point> points) {
        if (points == null) {
            return null;
        }

        List<PointDTO> list = new ArrayList<>(points.size());
        for (Point point : points) {
            list.add(toDTO(point));
        }

        return list;
    }

    @Override
    public Point toEntity(PointDTO pointDTO) {
        Point point = pointService.findById(pointDTO.getId());
        if ( point == null) {
            point = new Point();
        }

        point.setName(pointDTO.getName().trim());
        point.setDescription(pointDTO.getDescription());
        point.setStatus(pointDTO.isStatus());
        point.setLocation(locationService.findById(pointDTO.getLocationId()));

        return point;
    }

}
