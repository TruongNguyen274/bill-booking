package vn.billbooking.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import vn.billbooking.model.entity.Account;
import vn.billbooking.service.CustomUserDetails;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        String redirectURL = "/dang-nhap";
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = customUserDetails.getUser();

            if (account != null) {
                if (account.getRole() != null) {
                    String role = account.getRole().getName();
                    switch (role) {
                        case "ADMIN":
                        case "OWNER":
                            redirectURL = "/quan-ly/trang-chu";
                            break;
                        case "USER":
                            redirectURL = "/trang-chu.html";
                            break;
                        default:
                            redirectURL = "/trang-chu.html";
                            break;
                    }
                }
            }

            response.sendRedirect(redirectURL);
        } catch (Exception ex) {
            response.sendRedirect(redirectURL);
        }
    }

}
