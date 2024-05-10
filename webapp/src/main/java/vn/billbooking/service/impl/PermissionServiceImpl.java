package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.AccountPermission;
import vn.billbooking.model.entity.Permission;
import vn.billbooking.repository.AccountPermissionRepository;
import vn.billbooking.repository.PermissionRepository;
import vn.billbooking.service.AccountPermissionService;
import vn.billbooking.service.PermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAllByActive() {
        return permissionRepository.findByStatusIsTrue();
    }

}
