package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
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
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.AccountValidator;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quan-ly/tai-khoan")
public class AccountManagerController {
    private final String redirectUrl = "/quan-ly/tai-khoan/";

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
    private AccountValidator accountValidator;

    @GetMapping(value = {"", "/"})
    public String index(Model model) {
        try {
            List<Account> accounts = accountService.findAll();
            model.addAttribute("accountList", accountMapper.toListDTO(accounts));
            return "backend/account_list";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model) {
        try {
            model.addAttribute("accountDTO", new AccountDTO());
            model.addAttribute("locationList", locationService.findAll());
            model.addAttribute("roleList", roleService.findAll());
            return "backend/account_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Account account = accountService.findById(id);
            if (account == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("accountDTO", accountMapper.toDTO(account));
            model.addAttribute("locationList", locationService.findAll());
            model.addAttribute("roleList", roleService.findAll());
            model.addAttribute("errorList", new HashMap<>());

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/account_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }

    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, AccountDTO accountDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/tai-khoan/";
        try {
            // verify value
            accountValidator.validate(accountDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("locationList", locationService.findAll());
                model.addAttribute("roleList", roleService.findAll());
                return "/backend/account_form";
            } else {
                // save
                accountService.save(accountDTO);
                redirectUrl = "/quan-ly/tai-khoan/bieu-mau/" + accountDTO.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }
}
