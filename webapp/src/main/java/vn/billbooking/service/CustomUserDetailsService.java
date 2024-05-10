package vn.billbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.AccountPermission;
import vn.billbooking.model.entity.Permission;
import vn.billbooking.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AccountPermissionService accountPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByUsername(username);
        if (optional == null) {
            if (username.contains("@")) {
                optional = accountRepository.findByEmail(username);
            } else {
                optional = accountRepository.findByPhone(username);
            }
        }

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(optional.get(), getAuthorities(optional.get()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(Account account) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        try {
            String roleName = account.getRole().getName();
            switch (roleName.toUpperCase()) {
                case "USER":
                    authorities.add(new SimpleGrantedAuthority("USER"));
                    break;
                case "OWNER":
                    authorities.add(new SimpleGrantedAuthority("OWNER"));
                    authorities.add(new SimpleGrantedAuthority("BOOKING_MANAGER"));
                    break;
                case "ADMIN":
                    if (account.getRole().getName().equalsIgnoreCase("ADMIN")) {
                        authorities.add(new SimpleGrantedAuthority("ADMIN"));
                        // permission
                        List<Permission> permissionList = permissionService.findAllByActive();
                        if (permissionList != null) {
                            for (Permission permission : permissionList) {
                                if (permission.isStatus()) {
                                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                                }
                            }
                        }
                    } else {
                        List<AccountPermission> accountPermissionList = accountPermissionService.findByAccount(account);
                        if (accountPermissionList != null) {
                            for (AccountPermission accountPermission : accountPermissionList) {
                                if (accountPermission.isStatus()) {
                                    authorities.add(new SimpleGrantedAuthority(accountPermission.getPermission().getName()));
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
        }
        return authorities;
    }

}
