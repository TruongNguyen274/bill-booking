package vn.billbooking.model.dto;

import lombok.Getter;
import lombok.Setter;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Comment;

import java.util.List;

@Getter
@Setter
public class DashboardDTO {
    private int totalAccount;
    private int totalOwner;
    private int totalCustomer;
    private int totalBookingPending;
    private List<Booking> bookingOwner;
    private List<Booking> bookingCustomer;
    //owner
    private int totalRoom;
    private int totalPost;
    private int totalPromotion;
    private int totalBooking;
    private List<Comment> comments;
    private List<Booking> bookings;
    //total report
    private int reportTotalBooking;
    private int reportTotalBookingPending;
    private int reportTotalBookingApproved;
    private String reportTotalPrice = "0";

}
