package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByStatusIsTrue();

    Optional<Account> findByUsername(String name);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByPhone(String phone);

    List<Account> findByRoleAndStatusIsTrue(Role role);

    @Query(value = "SELECT * FROM account WHERE role_id = 2 AND status = 1 AND id NOT IN (SELECT account_id FROM karaoke)", nativeQuery = true)
    List<Account> getOwnerActiveKaraoke();
}
