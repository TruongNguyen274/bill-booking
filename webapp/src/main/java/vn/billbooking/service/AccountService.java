package vn.billbooking.service;

import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Role;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    List<Account> findAllByActive();

    Account findById(long id);

    Account findByUsername(String username);

    Account findByEmail(String email);

    Account findByPhone(String phone);

    Account save(Account account);

    Account save(AccountDTO accountDTO);

    Account save(AccountDTO accountDTO, String roleType);

    List<Account> findByRoleAndStatusIsTrue(Role role);

    List<Account> getOwnerActiveKaraoke();

}
