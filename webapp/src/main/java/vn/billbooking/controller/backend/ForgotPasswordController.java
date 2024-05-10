package vn.billbooking.controller.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.EmailAccountDTO;
import vn.billbooking.model.dto.MailDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.EmailService;
import vn.billbooking.service.impl.EmailServiceImpl;
import vn.billbooking.utils.DateUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.ForgotPasswordValidator;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/quan-ly/quen-mat-khau")
public class ForgotPasswordController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ValidatorUtil validatorUtil;

    @Autowired
    ForgotPasswordValidator forgotPasswordValidator;

    @GetMapping(value = {"", "/"})
    public String index(Model model) {
        try {
            AccountDTO accountDTO = new AccountDTO();
            model.addAttribute("error", null);
            model.addAttribute("accountDTO", accountDTO);
            return "backend/forgot_password";
        } catch (Exception ex) {
            return "redirect:/login";
        }
    }

    @PostMapping(value = {"", "/"})
    public String forgotPassword(Model model, @Valid AccountDTO accountDTO) {
        try {
            String msg = null;

            String email = accountDTO.getEmail();
            if (email == null || email.trim().equalsIgnoreCase("") || !email.contains("@")) {
                msg = "Vui lòng nhập địa chỉ Email của bạn!";
            } else {
                Account account = accountService.findByEmail(accountDTO.getEmail());
                if (account == null) {
                    msg = "Tài khoản không tồn tại trong hệ thống!";
                } else {

                    // send mail
                    EmailAccountDTO emailDTO = new EmailAccountDTO();
                    emailDTO.setId(account.getId());
                    emailDTO.setFullname(account.getFullName());
                    emailDTO.setEmail(account.getEmail());

                    emailService.sendEmailForResetPassword(emailDTO);
                    msg = "Yêu cầu cấp lại mật khẩu mới thành công!";
                }
            }

            model.addAttribute("error", msg);
            model.addAttribute("accountDTO", accountDTO);

            return "backend/forgot_password";
        } catch (Exception ex) {
            String redirectUrl = "/quan-ly/quen-mat-khau";
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"", "/xac-nhan"})
    public String confirmForgotPassword(Model model, @RequestParam String ref) {
        try {
            AccountDTO accountDTO = new AccountDTO();
            if (ref == null || ref.equalsIgnoreCase("")) {
                model.addAttribute("error", "Tài khoản không tồn tại trong hệ thống!");
                model.addAttribute("accountDTO", accountDTO);
            } else {
                String result = EmailServiceImpl.decoderBase64ToString(ref);
                ObjectMapper objectMapper = new ObjectMapper();
                MailDTO mailDTO = objectMapper.readValue(result, MailDTO.class);
                Date currentTime = new Date();
                Date time = DateUtil.convertStringToDate(mailDTO.getTime(), "HH:mm:ss dd-MM-yyyy");

                // adding 30 mins to the time
                Date afterAdding30Mins = new Date(time.getTime() + (30 * 60 * 1000));
                if (currentTime.after(afterAdding30Mins)) {
                    model.addAttribute("errorExpire", "Đã hết thời gian xác thực tài khoản, " +
                            "vui lòng gửi lại yêu cầu!");
                    model.addAttribute("accountDTO", accountDTO);
                    return "backend/forgot_password";
                } else {
                    LinkedHashMap<String, String> linkedHashMap = ((LinkedHashMap) mailDTO.getData());
                    Long id = Long.parseLong(String.valueOf(linkedHashMap.get("id")));
                    accountDTO.setId(id);
                    model.addAttribute("accountDTO", accountDTO);
                    model.addAttribute("errorList", new HashMap<>());
                }
            }

            return "backend/forgot_password_form";
        } catch (Exception ex) {
            String redirectUrl = "/quan-ly/quen-mat-khau";
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = {"", "/xac-nhan"})
    public String confirmForgotPassword(Model model, AccountDTO accountDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/quen-mat-khau/xac-nhan";
        try {
            Account account = accountService.findById(accountDTO.getId());
            //validate
            forgotPasswordValidator.validate(accountDTO, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "backend/forgot_password_form";
            } else {
                String encodedPassword = passwordEncoder.encode(accountDTO.getNewPassword());
                account.setPassword(encodedPassword);
                accountService.save(account);
                model.addAttribute("changePassword", "changePassword");
                return "backend/login";
            }
        } catch (Exception ex) {
            return "redirect:" + redirectUrl;
        }
    }
}
