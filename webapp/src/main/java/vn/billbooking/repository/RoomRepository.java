package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Room;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


    Optional<Room> findByName(String name);

    List<Room> findByKaraokeAndStatusIsTrue(Karaoke karaoke);

    List<Room> findByKaraoke(Karaoke karaoke);

    Optional<Room> findByKaraokeAndName(Karaoke karaoke, String name);
}
