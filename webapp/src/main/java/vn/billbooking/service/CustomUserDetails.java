package vn.billbooking.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.billbooking.model.entity.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Account account;
    private List<SimpleGrantedAuthority> authorities;

    public CustomUserDetails(Account account, List<SimpleGrantedAuthority> authorities) {
        this.account = account;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        // set role
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getName()));

        // set permission
        grantedAuthorities.addAll(authorities);

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.isStatus();
    }

    public Account getUser(){
        return this.account;
    }

    public String getFullName() {
        return this.account.getFullName();
    }

}
