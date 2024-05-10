package vn.billbooking.controller.frontend;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {

    @GetMapping(value = {"/tai-khoan/dang-xuat.html"})
    public String homePage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                request.getSession().invalidate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String redirectUrl = "/dang-nhap.html";
        return "redirect:" + redirectUrl;
    }

}
