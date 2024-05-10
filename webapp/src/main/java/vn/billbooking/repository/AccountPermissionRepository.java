package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.AccountPermission;

import java.util.List;

@Repository
public interface AccountPermissionRepository extends JpaRepository<AccountPermission, Long> {

    List<AccountPermission> findByStatusIsTrue();

    List<AccountPermission> findByAccount(Account account);

}
