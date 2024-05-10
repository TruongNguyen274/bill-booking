package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quan-ly/profile")
public class ProfileManagerController {
    private final String redirectUrl = "/quan-ly/profile/";
    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private AccountFormValidator accountFormValidator;

    @GetMapping(value = {"", "/"})
    public String edit(Model model, Authentication authentication, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            Account accountDB = accountService.findById(account.getId());
            if (account == null) {
                String redirectUrl = "/quan-ly/profile/";
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("accountDTO", accountMapper.toDTO(accountDB));
            model.addAttribute("locationList", locationService.findAll());
            model.addAttribute("roleList", roleService.findAll());
            model.addAttribute("errorList", new HashMap<>());

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/profile";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, AccountDTO accountDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/profile/";

        try {
            // verify value
            accountFormValidator.validate(accountDTO, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("locationList", locationService.findAll());
                model.addAttribute("roleList", roleService.findAll());
                return "/backend/profile";
            } else {
                // save
                accountService.save(accountDTO);
                redirectUrl = "/quan-ly/profile?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }
}
