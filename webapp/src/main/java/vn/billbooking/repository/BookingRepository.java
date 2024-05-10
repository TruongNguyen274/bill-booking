package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Karaoke;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByOwner(Karaoke owner);

    List<Booking> findByProgress(String progress);

    List<Booking> findByOwnerAndProgress(Karaoke owner, String progress);

    List<Booking> findByAccount(Account account);

    Booking findByCode(String orderId);

    @Query(value = "SELECT * FROM booking where time_order BETWEEN :startDate and :endDate and owner_id = :ownerId order by time_order DESC", nativeQuery = true)
    List<Booking> findReportByOwner(long ownerId, String startDate, String endDate);

    @Query(value = "SELECT * FROM booking where time_order BETWEEN :startDate and :endDate order by time_order DESC", nativeQuery = true)
    List<Booking> findReport(String startDate, String endDate);

}
