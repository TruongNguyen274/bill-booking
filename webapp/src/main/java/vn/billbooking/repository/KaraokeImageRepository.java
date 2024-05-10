package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.KaraokeImage;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface KaraokeImageRepository extends JpaRepository<KaraokeImage, Long> {

    List<KaraokeImage> findByKaraoke(Karaoke karaoke);

    @Modifying
    @Transactional
    @Query(value = "delete from karaoke_image where id =:id", nativeQuery = true)
    void deleteKaraokeImage(@Param("id") long id);
}
