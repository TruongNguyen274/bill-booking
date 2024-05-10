package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;

import java.util.List;
import java.util.Optional;

@Repository
public interface KaraokeRepository extends JpaRepository<Karaoke, Long> {

    Optional<Karaoke> findByName(String name);

    @Query(value="SELECT * FROM Karaoke WHERE status = TRUE ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Karaoke> findByRandom(int limit);

    @Query(value="SELECT * FROM Karaoke WHERE location_id = :locationId AND status = TRUE ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Karaoke> findByLocation(int limit, long locationId);

    @Query(value="SELECT * FROM Karaoke WHERE location_id = :locationId AND status = TRUE", nativeQuery = true)
    List<Karaoke> findByLocation(long locationId);

    @Query(value="SELECT * FROM Karaoke k WHERE k.point_id IN (:pointIds) AND k.status = TRUE", nativeQuery = true)
    List<Karaoke> findByPoint(String[] pointIds);

    List<Karaoke> findByAccount(Account account);

    @Query(value = "SELECT COUNT(k.id) FROM Karaoke k WHERE k.point_id = :pointId AND k.status = TRUE", nativeQuery = true)
    int countByPoint(long pointId);

    @Query(value = "SELECT k.* FROM Karaoke k WHERE k.name LIKE %:keyword% AND k.status = TRUE", nativeQuery = true)
    List<Karaoke> searchByKeyword(String keyword);

    @Query(value = "SELECT k.* FROM Karaoke k WHERE k.location_id = :locationId AND k.name LIKE %:keyword% AND k.status = TRUE", nativeQuery = true)
    List<Karaoke> searchByKeywordAndLocation(String keyword, long locationId);

}
