package vn.billbooking.service;

import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.AccountPermission;

import java.util.List;

public interface AccountPermissionService {

    List<AccountPermission> findAllByActive();

    List<AccountPermission> findByAccount(Account account);

}
