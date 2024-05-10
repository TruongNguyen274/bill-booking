package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.mapper.AccountMapper;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.RoleService;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.AccountFormValidator;
import vn.billbooking.validator.PasswordValidator;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quan-ly/thay-doi-mat-khau")
public class PasswordManagerController {
    private final String redirectUrl = "/quan-ly/thay-doi-mat-khau/";

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    PasswordValidator passwordValidator;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = {"/"})
    public String view(Model model) {
        try {
            model.addAttribute("accountDTO", new AccountDTO());
            model.addAttribute("errorList", new HashMap<>());

            return "backend/password_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/")
    public String save(Model model, AccountDTO accountDTO, Authentication authentication, BindingResult bindingResult, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        String redirectUrl = "/quan-ly/thay-doi-mat-khau/";
        try {
            Account account = ObjectUtil.getAccount(authentication);
            Account accountDB = accountService.findById(account.getId());
            accountDTO.setId(account.getId());
            if (account == null) {
                return "redirect:" + redirectUrl;
            }
            // verify value
            passwordValidator.validate(accountDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "/backend/password_form";
            } else {
                // save
                String encodedPassword = passwordEncoder.encode(accountDTO.getNewPassword());
                accountDB.setPassword(encodedPassword);
                accountService.save(accountDB);
                redirectUrl = "/quan-ly/dang-xuat/thay-doi-mat-khau";

                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }
}
