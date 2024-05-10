package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.service.LocationService;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationMapperImpl implements LocationMapper {

    @Autowired
    private LocationService locationService;

    @Override
    public LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setDescription(location.getDescription());
        locationDTO.setStatus(location.isStatus());

        return locationDTO;
    }

    @Override
    public List<LocationDTO> toListDTO(List<Location> locations) {
        if (locations == null) {
            return null;
        }

        List<LocationDTO> list = new ArrayList<>(locations.size());
        for (Location location : locations) {
            list.add(toDTO(location));
        }

        return list;
    }

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        Location location = locationService.findById(locationDTO.getId());
        if ( location == null) {
            location = new Location();
        }

        location.setName(locationDTO.getName().trim());
        location.setDescription(locationDTO.getDescription());
        location.setStatus(locationDTO.isStatus());

        return location;
    }

}
