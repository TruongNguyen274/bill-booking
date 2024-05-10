package vn.billbooking.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Role;
import vn.billbooking.service.CustomUserDetails;

@Controller
public class RedirectController {

    @GetMapping(value = {"/chuyen-huong/dang-nhap/thanh-cong"})
    public String redirectLogin(Authentication authentication) {
        String redirectUrl = "/trang-chu";

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getUser();
        if (account != null) {
            Role role = account.getRole();
            if (role != null) {
                switch (role.getName().toUpperCase()) {
                    case "ADMIN":
                        redirectUrl = "/quan-ly/trang-chu";
                        break;
                    case "OWNER":
                        redirectUrl = "/doi-tac/trang-chu";
                        break;
                    case "USER":
                        redirectUrl = "/trang-chu";
                        break;
                    default:
                        break;
                }
            }
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping(value = {"/chuyen-huong/dang-nhap/that-bai"})
    public String redirectLoginFaile(Model model, @RequestParam(required = false) String p,
              @RequestParam(required = false) String username) {
        String redirectUrl = "/trang-chu";

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(username);
        accountDTO.setPassword("");

        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");

        if (p != null) {
            switch (p.toUpperCase()) {
                case "AD":
                    return "backend/login";
                case "HP":
                    return "frontend/login";
                default:
                    return "redirect:" + redirectUrl;
            }
        }

        return "redirect:" + redirectUrl;
    }

}
