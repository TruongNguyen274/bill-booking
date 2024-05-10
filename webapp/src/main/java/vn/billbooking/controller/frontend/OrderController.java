package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.mapper.BookingMapper;
import vn.billbooking.service.BookingService;
import vn.billbooking.service.CustomUserDetails;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    @GetMapping(value = {"/don-hang.html"})
    public String homePage(Model model, @RequestParam(required = false) String orderId,
                           @RequestParam(required = false) String msg) {
        Booking booking = bookingService.findByCode(orderId);
        if (booking == null) {

        }

        model.addAttribute("bookingDTO", bookingMapper.toDTO(booking));
        model.addAttribute("msg", msg);

        return "frontend/order";
    }

}
