package vn.billbooking.utils;

import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import vn.billbooking.model.entity.Account;
import vn.billbooking.service.CustomUserDetails;

public class ObjectUtil {

    public static Account getAccount(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = new Account();
        if (!ObjectUtils.isEmpty(customUserDetails)) {
            account = customUserDetails.getUser();
        }
        return account;
    }

}
