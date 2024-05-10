package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.RoomDTO;
import vn.billbooking.model.entity.Room;

import java.util.List;

public interface RoomMapper {

    //Map Entity to DTO
    RoomDTO toDTO(Room room);

    List<RoomDTO> toListDTO(List<Room> rooms);

    //Map DTO to Entity
    Room toEntity(RoomDTO roomDTO);


}
