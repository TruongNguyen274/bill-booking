package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Room;
import vn.billbooking.repository.RoomRepository;
import vn.billbooking.service.RoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        List<Room> listRoom = roomRepository.findAll();
        return listRoom;
    }

    @Override
    public Room findById(long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room findByName(String name) {
        return roomRepository.findByName(name).orElse(null);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findByKaraokeAndStatusIsTrue(Karaoke karaoke) {
        return roomRepository.findByKaraokeAndStatusIsTrue(karaoke);
    }

    @Override
    public List<Room> findByKaraoke(Karaoke karaoke) {
        return roomRepository.findByKaraoke(karaoke);
    }

    @Override
    public Room findByKaraokeAndName(Karaoke karaoke, String name) {
        return roomRepository.findByKaraokeAndName(karaoke, name).orElse(null);
    }


}
