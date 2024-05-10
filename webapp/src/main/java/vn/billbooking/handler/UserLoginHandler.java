package vn.billbooking.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.dto.QuickStoreDTO;
import vn.billbooking.model.dto.UserLoginDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Comment;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.service.BookingService;
import vn.billbooking.service.CommentService;
import vn.billbooking.service.CustomUserDetails;
import vn.billbooking.service.LocationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class UserLoginHandler {

    @Autowired
    private LocationService locationService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LocationMapper locationMapper;

    @ModelAttribute("userLogin")
    public UserLoginDTO getCurrentUser(Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            Account account = null;
            if (customUserDetails != null) {
                account = customUserDetails.getUser();
            }

            UserLoginDTO userLoginDTO = null;

            if (account != null) {
                userLoginDTO = new UserLoginDTO();
                userLoginDTO.setFullName(account.getFullName());
                userLoginDTO.setUsername(account.getUsername());
                userLoginDTO.setEmail(account.getEmail());
                userLoginDTO.setPhone(account.getPhone());
            }

            return userLoginDTO;
        }

        return null;
    }

    @ModelAttribute("locationStore")
    public List<LocationDTO> getLocation() {
        List<Location> locationList =  locationService.findByActive();
        if (locationList != null) {
            return locationMapper.toListDTO(locationList);
        }

        return null;
    }

    @ModelAttribute("localStore")
    public LocationDTO getLocal(@RequestParam(name = "locationId", required = false) String locationId) {
        if (locationId == null || locationId.isEmpty()) {
            List<Location> locationList =  locationService.findByActive();
            if (locationList != null) {
                return locationMapper.toListDTO(locationList).get(0);
            }
        }

        Location location =  locationService.findById(Long.parseLong(locationId));
        if (location != null) {
            return locationMapper.toDTO(location);
        }

        return null;
    }

    @ModelAttribute("quickStore")
    public QuickStoreDTO getTotal(HttpServletRequest request) {
        QuickStoreDTO quickStoreDTO = new QuickStoreDTO();
        try {
            String requestURL = request.getRequestURL().toString();
            if (requestURL != null && requestURL.contains("/quan-ly/")) {
                // total booking
                int totalBooking = 0;
                List<Booking> bookingList = bookingService.findByProgress("PENDING");
                if (!bookingList.isEmpty()) {
                    totalBooking = bookingList.size();
                }
                quickStoreDTO.setTotalBooking(totalBooking);

                // total comment
                int totalComment = 0;
                List<Comment> commentList = commentService.findByProgress("PENDING");
                if (!commentList.isEmpty()) {
                    totalComment = commentList.size();
                }
                quickStoreDTO.setTotalComment(totalComment);
            }
        } catch (Exception ex) {
            return null;
        }

        return quickStoreDTO;
    }

}
