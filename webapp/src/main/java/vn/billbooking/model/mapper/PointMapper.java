package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.PointDTO;
import vn.billbooking.model.entity.Point;

import java.util.List;

public interface PointMapper {

    // Map Entity to DTO
    PointDTO toDTO(Point point);

    List<PointDTO> toListDTO(List<Point> points);

    // Map DTO to Entity
    Point toEntity(PointDTO pointDTO);
    
}
