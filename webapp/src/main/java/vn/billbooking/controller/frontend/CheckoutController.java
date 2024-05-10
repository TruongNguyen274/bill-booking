package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.dto.CheckoutDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.service.BookingService;
import vn.billbooking.service.CustomUserDetails;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.LocationService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.CheckoutValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private CheckoutValidator checkoutValidator;

    @Autowired
    private ValidatorUtil validatorUtil;

    @GetMapping(value = {"/thanh-toan.html"})
    public String homePage(Model model, Authentication authentication, @RequestParam(required = false) String location,
            @RequestParam(required = false) String ownerId) {
        if (ownerId == null) {
            String redirectUrl = "/dang-nhap.html";
            return "redirect:" + redirectUrl;
        }

        CheckoutDTO checkoutDTO = new CheckoutDTO();

        Karaoke karaoke = karaokeService.findById(Long.parseLong(ownerId));
        if (karaoke == null) {
            String redirectUrl = "/dang-nhap.html";
            return "redirect:" + redirectUrl;
        }

        checkoutDTO.setOwnerId(Long.parseLong(ownerId));

        long locationId = 0;

        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = customUserDetails.getUser();
            Location loc = account.getLocation();
            if (loc != null) {
                locationId = loc.getId();
            }

            checkoutDTO.setAccountId(account.getId());
            checkoutDTO.setFullName(account.getFullName());
            checkoutDTO.setEmail(account.getEmail());
            checkoutDTO.setPhone(account.getPhone());
        }

        if (authentication == null || locationId == 0) {

        }

        List<Location> locationList = locationService.findByActive();

        model.addAttribute("checkoutDTO", checkoutDTO);
        model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));
        model.addAttribute("locationList", locationMapper.toListDTO(locationList));

        return "frontend/checkout";
    }

    @PostMapping(value = {"/thanh-toan.html"})
    public String register(Model model, Authentication authentication, @Valid CheckoutDTO checkoutDTO, BindingResult bindingResult) {
        String redirectUrl = null;
        try {
            // verify
            checkoutValidator.validate(checkoutDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                Karaoke karaoke = karaokeService.findById(checkoutDTO.getOwnerId());
                if (karaoke == null) {
                    redirectUrl = "/dang-nhap.html";
                    return "redirect:" + redirectUrl;
                }

                model.addAttribute("error", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("checkoutDTO", checkoutDTO);
                model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));
                return "frontend/checkout";
            } else {
                Booking booking = bookingService.save(checkoutDTO);
                if (booking == null) {
                    redirectUrl = "/don-hang.html";
                } else {
                    redirectUrl = "/don-hang.html?orderId=" + booking.getCode() + "&msg=success";
                }

                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            redirectUrl = "/dang-nhap.html";
            return "redirect:" + redirectUrl;
        }
    }

}
