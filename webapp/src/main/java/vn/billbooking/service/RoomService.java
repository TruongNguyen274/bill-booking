package vn.billbooking.service;

import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<Room> findAll();

    Room findById(long id);

    Room findByName(String name);

    Room save(Room room);

    List<Room> findByKaraokeAndStatusIsTrue(Karaoke karaoke);

    List<Room> findByKaraoke(Karaoke karaoke);

    Room findByKaraokeAndName(Karaoke karaoke, String name);




}
