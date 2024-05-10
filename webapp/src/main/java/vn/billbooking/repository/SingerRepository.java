package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Singer;

import java.util.List;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

    List<Singer> findByStatusIsTrue();

}
