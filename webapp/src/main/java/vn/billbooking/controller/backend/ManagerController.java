package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.billbooking.model.dto.DashboardDTO;
import vn.billbooking.model.entity.*;
import vn.billbooking.service.*;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ObjectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/quan-ly")
public class ManagerController {
    private final String redirectUrl = "/quan-ly/trang-chu";
    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PostService postService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AccountPermissionService accountPermissionService;

    @GetMapping(value = {"/trang-chu"})
    public String homePage(Model model, HttpSession httpSession,  Authentication authentication) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            Karaoke karaoke = null;
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
                List<Karaoke> karaokes = karaokeService.findByAccount(account);
                if (!ObjectUtils.isEmpty(karaokes)) {
                    karaoke = karaokes.get(0);
                }
            }

            // dashboard
            DashboardDTO dashboardDTO = new DashboardDTO();
            List<Account> accounts = accountService.findAll();
            if (!CollectionUtils.isEmpty(accounts)) {
                dashboardDTO.setTotalAccount(accounts.size());

                List<Account> resultOwner = accounts.stream()
                        .filter(object -> object.getRole().getId() == ContantUtil.ROLE_OWNER)
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(resultOwner)) {
                    dashboardDTO.setTotalOwner(resultOwner.size());
                }

                List<Account> resultCustomer = accounts.stream()
                        .filter(object -> object.getRole().getId() == ContantUtil.ROLE_CUSTOMER)
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(resultOwner)) {
                    dashboardDTO.setTotalCustomer(resultCustomer.size());
                }
            }

            // booking
            List<Booking> bookings = bookingService.findByProgress(ContantUtil.PROGRESS_PENDING);
            if (!CollectionUtils.isEmpty(bookings)) {
                dashboardDTO.setTotalBookingPending(bookings.size());

                List<Booking> bookingOwner = bookings.stream()
                        .filter(object -> object.getAccount().getRole().getId() == ContantUtil.ROLE_OWNER)
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(bookingOwner)) {
                    dashboardDTO.setBookingOwner(bookingOwner);
                }

                List<Booking> bookingCustomer = bookings.stream()
                        .filter(object -> object.getAccount().getRole().getId() == ContantUtil.ROLE_CUSTOMER)
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(bookingCustomer)) {
                    dashboardDTO.setBookingCustomer(bookingCustomer);
                }
            }

            // total room, post, promotion
            if (!ObjectUtils.isEmpty(karaoke)) {
                List<Room> rooms = roomService.findByKaraoke(karaoke);
                if (!CollectionUtils.isEmpty(rooms)) {
                    dashboardDTO.setTotalRoom(rooms.size());
                }

                List<Post> posts = postService.findByOwner(karaoke);
                if (!CollectionUtils.isEmpty(posts)) {
                    dashboardDTO.setTotalPost(posts.size());
                }

                List<Promotion> promotions = promotionService.findByOwner(karaoke);
                if (!CollectionUtils.isEmpty(promotions)) {
                    dashboardDTO.setTotalPromotion(promotions.size());
                }

                List<Comment> comments = commentService.findByKaraoke(karaoke);
                dashboardDTO.setComments(comments);
                List<Booking> bookingList = bookingService.findByOwnerAndProgress(karaoke, ContantUtil.PROGRESS_PENDING);
                if (!CollectionUtils.isEmpty(bookingList)) {
                    dashboardDTO.setTotalBooking(bookingList.size());
                    dashboardDTO.setBookings(bookingList);
                }
            }
            model.addAttribute("dashboardDTO", dashboardDTO);

            // set permission
            HashMap<String, Boolean> permissionHashMap = new HashMap<>();

            if (account.getRole().getName().equalsIgnoreCase("OWNER")) {
                permissionHashMap.put("BOOKING_MANAGER", true);
            } else {
                if (account.getRole().getName().equalsIgnoreCase("ADMIN")) {
                    List<Permission> permissionList = permissionService.findAllByActive();
                    if (permissionList != null) {
                        for (Permission permission : permissionList) {
                            if (permission.isStatus()) {
                                permissionHashMap.put(permission.getName(), true);
                            }
                        }
                    }
                } else {
                    List<AccountPermission> accountPermissionList = accountPermissionService.findByAccount(account);
                    if (accountPermissionList != null) {
                        for (AccountPermission accountPermission : accountPermissionList) {
                            if (accountPermission.isStatus()) {
                                permissionHashMap.put(accountPermission.getPermission().getName(), true);
                            }
                        }
                    }
                }
            }
            httpSession.setAttribute("permissionList", permissionHashMap);

            if (roleId == ContantUtil.ROLE_OWNER) {
                return "backend/dashboard_owner";
            }

            return "backend/dashboard_manager";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping("/dang-nhap")
    public String loginPage() {
        return "backend/login";
    }

    @GetMapping(value = {"/dang-xuat"})
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                request.getSession().invalidate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "backend/login";
    }

    @GetMapping(value = {"/dang-xuat/thay-doi-mat-khau"})
    public String logoutChangePass(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                request.getSession().invalidate();
            }
            model.addAttribute("changePassword", "changePassword");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "backend/login";
    }

//    @GetMapping(value = {"/dang-xuat/quen-mat-khau"})
//    public String logoutForgotPass(HttpServletRequest request, HttpServletResponse response, Model model) {
//        try {
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            if (auth != null) {
//                new SecurityContextLogoutHandler().logout(request, response, auth);
//                request.getSession().invalidate();
//            }
//            model.addAttribute("forgotPassword", "forgotPassword");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "backend/login";
//    }

}
