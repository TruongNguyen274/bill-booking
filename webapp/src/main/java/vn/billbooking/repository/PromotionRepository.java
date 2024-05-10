package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Promotion;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Optional<Promotion> findByName(String name);

    List<Promotion> findByOwner(Karaoke owner);

    List<Promotion> findByStatusIsTrue();

    @Query(value="SELECT * FROM Promotion WHERE location_id = :locationId AND status = TRUE ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Promotion> findByLocation(int limit, long locationId);

    @Query(value="SELECT * FROM Promotion WHERE status = TRUE ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Promotion> findByRandom(int limit);

    @Query(value="SELECT * FROM Promotion WHERE location_id = :locationId AND status = TRUE", nativeQuery = true)
    List<Promotion> findByLocation(long locationId);

    @Query(value="SELECT * FROM Promotion p WHERE p.location_id IN (:ids) AND p.status = TRUE", nativeQuery = true)
    List<Promotion> findByLocationIds(String[] ids);

    @Query(value = "SELECT COUNT(p.id) FROM Promotion p WHERE p.location_id = :locationId AND p.status = TRUE", nativeQuery = true)
    int countByLocation(long locationId);

}
