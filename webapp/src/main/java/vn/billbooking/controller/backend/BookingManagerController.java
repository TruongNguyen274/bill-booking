package vn.billbooking.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.BookingDTO;
import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.mapper.BookingMapper;
import vn.billbooking.service.*;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.BookingValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("quan-ly/dat-phong")
public class BookingManagerController {
    private final String redirectUrl = "/quan-ly/dat-phong/";
    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private ValidatorUtil validatorUtil;
    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    BookingValidator bookingValidator;

    @GetMapping(value = {"", "/"})
    public String list(Model model, Authentication authentication) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            boolean isAdmin = false;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }

            List<Booking> bookings = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                List<Karaoke> owner = karaokeService.findByAccount(account);
                if (!CollectionUtils.isEmpty(owner))
                bookings = bookingService.findByOwner(owner.get(0));
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                bookings = bookingService.findAll();
                isAdmin = true;
            }
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("bookingList", bookingMapper.toListDTO(bookings));
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }

        return "backend/booking_list";
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model, Authentication authentication) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Karaoke> karaokeList = new ArrayList<>();
            BookingDTO bookingDTO = new BookingDTO();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                model.addAttribute("isOwner", "OWNER");
                if (!CollectionUtils.isEmpty(karaokeList))
                bookingDTO.setOwnerId(karaokeList.get(0).getId());
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }
            model.addAttribute("bookingDTO", bookingDTO);
            model.addAttribute("karaokeList", karaokeList);
            model.addAttribute("accountList", accountService.findByRoleAndStatusIsTrue(roleService.findById(ContantUtil.ROLE_CUSTOMER)));
            return "backend/booking_form";
        } catch (Exception exception) {
            exception.getMessage();
            return redirectUrl;
        }

    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status, Authentication authentication) {
        String redirectUrl = "/quan-ly/dat-phong/";
        try {
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Karaoke> karaokeList = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }

            Booking booking = bookingService.findById(id);

            if (booking == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("bookingDTO", bookingMapper.toDTO(booking));
            model.addAttribute("errorList", new HashMap<>());
            model.addAttribute("karaokeList", karaokeList);
            model.addAttribute("accountList", accountService.findByRoleAndStatusIsTrue(roleService.findById(ContantUtil.ROLE_CUSTOMER)));

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/booking_form";
        } catch (Exception exception) {
            exception.getMessage();
            return redirectUrl;
        }

    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, BookingDTO bookingDTO, BindingResult bindingResult, Authentication authentication) {
        String redirectUrl = "/quan-ly/dat-phong/";
        int isConfirm = 0;

        try {
            bookingValidator.validate(bookingDTO, bindingResult);

            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            List<Karaoke> karaokeList = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                isConfirm = (int) ContantUtil.ROLE_OWNER;
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
                isConfirm = (int) ContantUtil.ROLE_ADMIN;
            }

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("karaokeList", karaokeList);
                model.addAttribute("accountList", accountService.findByRoleAndStatusIsTrue(roleService.findById(ContantUtil.ROLE_CUSTOMER)));

                return "/backend/booking_form";
            } else {
                Booking booking = bookingMapper.toEntity(bookingDTO);
                booking.setIsConfirm(isConfirm);
                if (bookingDTO.getBillMul() != null && !ObjectUtils.isEmpty(bookingDTO.getBillMul().getOriginalFilename())) {
                    FileDTO fileDTOBack = fileUploadService.uploadFile(bookingDTO.getBillMul());
                    booking.setBill(fileDTOBack.getPath());
                }
                bookingService.save(booking);

                redirectUrl = "/quan-ly/dat-phong/bieu-mau/" + booking.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

}

