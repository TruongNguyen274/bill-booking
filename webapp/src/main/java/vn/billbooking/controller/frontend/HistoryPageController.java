package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.mapper.AccountMapper;
import vn.billbooking.model.mapper.BookingMapper;
import vn.billbooking.service.BookingService;
import vn.billbooking.service.CustomUserDetails;

import java.util.List;

@Controller
public class HistoryPageController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping(value = {"/tai-khoan/lich-su.html"})
    public String homePage(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getUser();

        List<Booking> bookingList = bookingService.findByAccount(account);

        AccountDTO accountDTO = accountMapper.toDTO(account);

        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("bookingList", bookingMapper.toListDTO(bookingList));

        return "frontend/history";
    }

}
