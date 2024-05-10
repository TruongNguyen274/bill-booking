package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.entity.Location;

import java.util.List;

public interface LocationMapper {

    // Map Entity to DTO
    LocationDTO toDTO(Location location);

    List<LocationDTO> toListDTO(List<Location> locations);

    // Map DTO to Entity
    Location toEntity(LocationDTO locationDTO);
    
}
