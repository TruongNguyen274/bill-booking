package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.dto.RoleDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Role;
import vn.billbooking.model.mapper.AccountMapper;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.RoleService;
import vn.billbooking.utils.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Autowired
    private LocationService locationService;

    @Autowired
    private RoleService roleService;

    @Override
    public AccountDTO toDTO(Account account) {
        if (account == null) {
            return null;
        }

        // Account
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setCode(account.getCode());
        accountDTO.setAvatar(account.getAvatar());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword("******");
        accountDTO.setPhone(account.getPhone());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setVerifyEmail(account.isVerifyEmail());
        accountDTO.setVerifyPhone(account.isVerifyPhone());
        accountDTO.setStatus(account.isStatus());

        // Role
        if (account.getRole() != null) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(account.getRole().getId());
            roleDTO.setName(account.getRole().getName());
            roleDTO.setDescription(account.getRole().getDescription());
            roleDTO.setStatus(account.getRole().isStatus());
            accountDTO.setRole(roleDTO);
            accountDTO.setRoleId(roleDTO.getId());
        }

        // Location
        if (account.getLocation() != null) {
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setId(account.getLocation().getId());
            locationDTO.setName(account.getLocation().getDescription());
            locationDTO.setDescription(account.getLocation().getDescription());
            locationDTO.setStatus(account.getLocation().isStatus());
            accountDTO.setLocation(locationDTO);
            accountDTO.setLocationId(locationDTO.getId());
        }

        return accountDTO;
    }

    @Override
    public List<AccountDTO> toListDTO(List<Account> accounts) {
        if (accounts == null) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<>(accounts.size());
        for (Account account : accounts) {
            AccountDTO accountDTO = toDTO(account);
            if (accountDTO != null) {
                list.add(accountDTO);
            }
        }

        return list;
    }

    @Override
    public Account toEntity(Account account, AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        // set account
        account.setFullName(accountDTO.getFullName().trim());
        if (!ValidatorUtil.isEmpty(accountDTO.getEmail())) {
            account.setEmail(accountDTO.getEmail().trim());
        }
        if (!ValidatorUtil.isEmpty(accountDTO.getPhone())) {
            account.setPhone(accountDTO.getPhone().trim());
        }
        if (accountDTO.getUsername() != null) {
            account.setUsername(accountDTO.getUsername().trim());
        }
        account.setAddress(accountDTO.getAddress());
        account.setVerifyEmail(accountDTO.isVerifyEmail());
        account.setVerifyPhone(accountDTO.isVerifyPhone());
        account.setStatus(accountDTO.isStatus());

        // set avatar
        if (!ValidatorUtil.isEmpty(accountDTO.getAvatarMul())) {
            account.setAvatar(accountDTO.getAvatar());
        }

        // set role
        if (accountDTO.getRoleId() > 0) {
            Role role = roleService.findById(accountDTO.getRoleId());
            account.setRole(role);
        }

        // set location
        if (accountDTO.getLocationId() > 0) {
            Location location = locationService.findById(accountDTO.getLocationId());
            account.setLocation(location);
        }

        return account;
    }

}
