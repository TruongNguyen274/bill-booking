package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.AccountPermission;
import vn.billbooking.repository.AccountPermissionRepository;
import vn.billbooking.service.AccountPermissionService;

import java.util.List;

@Service
public class AccountPermissionServiceImpl implements AccountPermissionService {

    @Autowired
    private AccountPermissionRepository accountPermissionRepository;

    @Override
    public List<AccountPermission> findAllByActive() {
        return accountPermissionRepository.findByStatusIsTrue();
    }

    @Override
    public List<AccountPermission> findByAccount(Account account) {
        return accountPermissionRepository.findByAccount(account);
    }

}
