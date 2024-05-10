package vn.billbooking.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.DashboardDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.dto.ReportBookingDTO;
import vn.billbooking.model.entity.*;
import vn.billbooking.model.mapper.BookingMapper;
import vn.billbooking.service.*;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.DateUtil;
import vn.billbooking.utils.FormatUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.ReportValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/thong-ke")
public class ReportManagerController {
    private final String redirectUrl = "/thong-ke/dat-phong/";

    @Autowired
    AccountService accountService;

    @Autowired
    BookingService bookingService;

    @Autowired
    CommentService commentService;

    @Autowired
    RoomService roomService;

    @Autowired
    PostService postService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    KaraokeService karaokeService;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private ReportValidator reportValidator;

    @Autowired
    ValidatorUtil validatorUtil;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    FormatUtil formatUtil;

    @GetMapping(value = {"/dat-phong", "/dat-phong/"})
    public String booking(Model model, Authentication authentication) {
        try {
            ReportBookingDTO reportBookingDTO = new ReportBookingDTO();
            Date date = new Date();
            String dateStr = dateUtil.convertDateToString(date, "yyyy-MM-dd");
            reportBookingDTO.setStartDate(dateStr);
            reportBookingDTO.setEndDate(dateStr);
            List<Booking> bookingList = bookingService.findReport(reportBookingDTO);
            setDashboardDTO(model, bookingList);

            model.addAttribute("karaokes", karaokeService.findAll());
            model.addAttribute("bookingList", bookingMapper.toListDTO(bookingList));
            model.addAttribute("errorList", new HashMap<>());
            model.addAttribute("reportBookingDTO", reportBookingDTO);

            return "backend/report_booking";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/dat-phong/tim-kiem"})
    public String search(Model model, ReportBookingDTO reportBookingDTO, BindingResult bindingResult) {
        try {
            //validate
            reportValidator.validate(reportBookingDTO, bindingResult);
            List<Booking> bookingList = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("dashboardDTO", new DashboardDTO());
                model.addAttribute("karaokes", karaokeService.findAll());
                return "backend/report_booking";
            } else {
                if (reportBookingDTO.getKaraokeId() != 0) {
                    bookingList = bookingService.findReportByOwner(reportBookingDTO);
                    model.addAttribute("bookingList", bookingMapper.toListDTO(bookingList));
                } else {
                    bookingList = bookingService.findReport(reportBookingDTO);
                    model.addAttribute("bookingList", bookingMapper.toListDTO(bookingList));
                }
                setDashboardDTO(model, bookingList);
                model.addAttribute("karaokes", karaokeService.findAll());
                model.addAttribute("errorList", new HashMap<>());
            }
            return "backend/report_booking";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    private void setDashboardDTO(Model model, List<Booking> bookingList) {
        try {
            DashboardDTO dashboardDTO = new DashboardDTO();
            if (!CollectionUtils.isEmpty(bookingList)) {
                dashboardDTO.setReportTotalBooking(bookingList.size());

                List<Booking> bookingPending = bookingList.stream()
                        .filter(object -> object.getProgress().equals(ContantUtil.PROGRESS_PENDING))
                        .collect(Collectors.toList());
                dashboardDTO.setReportTotalBookingPending(bookingPending.size());

                List<Booking> bookingApproved = bookingList.stream()
                        .filter(object -> object.getProgress().equals(ContantUtil.PROGRESS_APPROVED))
                        .collect(Collectors.toList());
                dashboardDTO.setReportTotalBookingApproved(bookingApproved.size());

                double totalPrice = bookingList.stream().mapToDouble(i -> i.getTotalBill()).sum();
                dashboardDTO.setReportTotalPrice(formatUtil.formatNumber(totalPrice));
            }
            model.addAttribute("dashboardDTO", dashboardDTO);
        } catch (Exception ex) {
        }
    }
}

