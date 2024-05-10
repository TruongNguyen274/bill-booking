package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.service.AccountService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.RegisterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private ValidatorUtil validatorUtil;

    @GetMapping(value = {"/dang-ky.html"})
    public String registerPage(Model model) {
        String redirectUrl = null;
        try {
            AccountDTO accountDTO = new AccountDTO();

            model.addAttribute("error", null);
            model.addAttribute("accountDTO", accountDTO);

            return "frontend/register";
        } catch (Exception ex) {
            redirectUrl = "/dang-ky.html";
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = {"/dang-ky.html"})
    public String register(Model model, @Valid AccountDTO accountDTO, BindingResult bindingResult, HttpServletRequest request) {
        String redirectUrl = null;
        try {
            // verify
            registerValidator.validate(accountDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("error", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("accountDTO", accountDTO);
                return "frontend/register";
            } else {
                // save
                Account account = accountService.save(accountDTO, "USER");

                if (account != null) {
                    redirectUrl = "/dang-nhap.html";
                    return "redirect:" + redirectUrl;
                }

                redirectUrl = "/dang-ky.html";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            redirectUrl = "/dang-ky.html";
            return "redirect:" + redirectUrl;
        }
    }

}
