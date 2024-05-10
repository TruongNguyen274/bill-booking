package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.AccountMapper;
import vn.billbooking.model.mapper.BookingMapper;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.BookingService;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.service.LocationService;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.AccountChangePasswordValidator;
import vn.billbooking.validator.AccountFormValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfilePageController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private AccountFormValidator accountFormValidator;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private AccountChangePasswordValidator accountChangePasswordValidator;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    @GetMapping(value = {"/tai-khoan/thong-tin.html"})
    public String homePage(Model model, Authentication authentication) {
        Account account = ObjectUtil.getAccount(authentication);

        List<Booking> bookingList = bookingService.findByAccount(account);

        if (account == null) {
            String redirectUrl = "/trang-chu.html";
            return "redirect:" + redirectUrl;
        }
        AccountDTO accountDTO = accountMapper.toDTO(account);

        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("locationList", locationService.findAll());
        model.addAttribute("bookingList", bookingMapper.toListDTO(bookingList));
        return "frontend/profile";
    }

    @PostMapping(value = {"/tai-khoan/thong-tin.html"})
    public String changeProfile(Model model, Authentication authentication, @Valid AccountDTO accountDTO, BindingResult bindingResult) {
        String redirectUrl = null;
        try{
            Account account = ObjectUtil.getAccount(authentication);
            accountDTO.setId(account.getId());

            accountFormValidator.validate(accountDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("error", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("accountDTO", accountDTO);
                model.addAttribute("locationList", locationService.findAll());
                return "frontend/profile";
            } else {
                // location
                Location location = locationService.findById(accountDTO.getLocationId());

                // check image upload
                if (accountDTO.getAvatarMul() != null && !ObjectUtils.isEmpty(accountDTO.getAvatarMul().getOriginalFilename())) {
                    FileDTO fileDTO = fileUploadService.uploadFile(accountDTO.getAvatarMul());
                    account.setAvatar(fileDTO.getPath());
                }

                account.setFullName(accountDTO.getFullName());
                account.setEmail(accountDTO.getEmail());
                account.setPhone(accountDTO.getPhone());
                account.setAddress(accountDTO.getAddress());
                account.setLocation(location);

                accountService.save(account);

                redirectUrl = "/tai-khoan/thong-tin.html";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            redirectUrl = "/tai-khoan/thong-tin.html";
            return "redirect:" + redirectUrl;
        }
    }

}
