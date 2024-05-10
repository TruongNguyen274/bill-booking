package vn.billbooking.service;

import vn.billbooking.model.dto.CheckoutDTO;
import vn.billbooking.model.dto.ReportBookingDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Karaoke;

import java.util.List;

public interface BookingService {

    List<Booking> findAll();

    Booking findById(long id);

    Booking save(Booking booking);

    List<Booking> findByOwner(Karaoke owner);

    List<Booking> findByProgress(String progress);

    List<Booking> findByOwnerAndProgress(Karaoke owner, String progress);

    Booking save(CheckoutDTO checkoutDTO);

    List<Booking> findByAccount(Account account);

    Booking findByCode(String orderId);

    List<Booking> findReportByOwner(ReportBookingDTO reportBookingDTO);

    List<Booking> findReport(ReportBookingDTO reportBookingDTOe);

}
