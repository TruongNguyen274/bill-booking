package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.dto.RoomDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Room;
import vn.billbooking.model.mapper.RoomMapper;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.RoomService;
import vn.billbooking.utils.FormatUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomMapperImpl implements RoomMapper {

    @Autowired
    RoomService roomService;

    @Autowired
    FormatUtil formatUtil;

    @Autowired
    KaraokeService karaokeService;

    @Override
    public RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setDescription(room.getDescription());
        if (room.getRegularPrice() != null) {
            roomDTO.setRegularPrice(formatUtil.formatNumber(room.getRegularPrice()).replace(".00", ""));
        }
        roomDTO.setSalePrice(room.getSalePrice());
        roomDTO.setStatus(room.isStatus());

        // custom
        KaraokeDTO karaokeDTO = new KaraokeDTO();
        Karaoke karaoke = room.getKaraoke();
        if (karaoke != null) {
            karaokeDTO.setId(karaoke.getId());
            karaokeDTO.setName(karaoke.getName());
        }
        roomDTO.setKaraoke(karaokeDTO);
        roomDTO.setKaraokeId(karaoke.getId());

        return roomDTO;
    }

    @Override
    public List<RoomDTO> toListDTO(List<Room> rooms) {
        if (rooms == null) {
            return null;
        }

        List<RoomDTO> list = new ArrayList<>(rooms.size());
        for (Room room : rooms) {
            RoomDTO roomDTO = toDTO(room);
            if (roomDTO != null) {
                list.add(roomDTO);
            }
        }

        return list;
    }

    @Override
    public Room toEntity(RoomDTO roomDTO) {
        Room room = roomService.findById(roomDTO.getId());

        if (room == null) {
            room = new Room();
        }

        room.setName(roomDTO.getName().trim());
        room.setRoomType(roomDTO.getRoomType());
        room.setRegularPrice(formatUtil.formatNumber(roomDTO.getRegularPrice()));
//        if (roomDTO.getSalePrice() != null && !roomDTO.getSalePrice().trim().isEmpty()) {
//            room.setSalePrice(formatUtil.formatNumber(roomDTO.getSalePrice()));
//        }
        room.setSalePrice(roomDTO.getSalePrice());
        room.setDescription(roomDTO.getDescription());
        room.setStatus(roomDTO.isStatus());

        Karaoke karaoke = karaokeService.findById(roomDTO.getKaraokeId());
        room.setKaraoke(karaoke);
        return room;
    }

}
