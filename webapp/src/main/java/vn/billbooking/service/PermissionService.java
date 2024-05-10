package vn.billbooking.service;

import vn.billbooking.model.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAllByActive();

}
