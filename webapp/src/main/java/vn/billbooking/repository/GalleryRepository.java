package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Gallery;

import java.util.List;
import java.util.Optional;


@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    @Query(value="SELECT * FROM Gallery WHERE type = :type AND status = :status ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Gallery> findByRandom(int limit, String type, boolean status);

    @Query(value="SELECT * FROM Gallery WHERE type = :type AND status = true", nativeQuery = true)
    Optional<Gallery> findByType(String type);

}
