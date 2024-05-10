package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.BookingDTO;
import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.mapper.BookingMapper;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.BookingService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.utils.DateUtil;
import vn.billbooking.utils.FormatUtil;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookingMapperImpl implements BookingMapper {

    @Autowired
    BookingService bookingService;

    @Autowired
    KaraokeService karaokeService;

    @Autowired
    AccountService accountService;

    @Autowired
    FormatUtil formatUtil;

    @Override
    public BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }

        // booking
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCode(booking.getCode());
        bookingDTO.setPrice(formatUtil.formatNumber(booking.getPrice()));
        bookingDTO.setDiscount(booking.getDiscount());
        bookingDTO.setProgress(booking.getProgress());
        bookingDTO.setRoomType(booking.getRoomType());
        bookingDTO.setIsConfirm(booking.getIsConfirm());
        if (booking.getTimeOrder() != null) {
            bookingDTO.setTimeInput(DateUtil.convertDateToString(booking.getTimeOrder(), "MM/dd/yyyy h:mm a"));
        }
        bookingDTO.setBill(booking.getBill());
        bookingDTO.setTotalBill(formatUtil.formatNumber(booking.getTotalBill()));
        bookingDTO.setTotalPeople(booking.getTotalPeople());
        bookingDTO.setNote(booking.getNote());

        // owner
        KaraokeDTO karaokeDTO = new KaraokeDTO();
        Karaoke karaoke = booking.getOwner();
        if (karaoke != null) {
            karaokeDTO.setId(karaoke.getId());
            karaokeDTO.setName(karaoke.getName());
            karaokeDTO.setAddress(karaoke.getAddress());
            karaokeDTO.setPhone(karaoke.getPhone());
        }
        bookingDTO.setOwner(karaokeDTO);
        bookingDTO.setOwnerId(karaoke.getId());

        // account
        AccountDTO accountDTO = new AccountDTO();
        Account account = booking.getAccount();
        if (account != null) {
            accountDTO.setId(account.getId());
            accountDTO.setUsername(account.getUsername());
            accountDTO.setFullName(account.getFullName());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setPhone(account.getPhone());

        }
        bookingDTO.setAccount(accountDTO);
        bookingDTO.setAccountId(account.getId());

        return bookingDTO;
    }

    @Override
    public List<BookingDTO> toListDTO(List<Booking> bookings) {
        if (bookings == null) {
            return null;
        }
        List<BookingDTO> list = new ArrayList<>(bookings.size());
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = toDTO(booking);
            if (bookingDTO != null) {
                list.add(toDTO(booking));
            }
        }
        return list;
    }

    @Override
    public Booking toEntity(BookingDTO bookingDTO) {
        Booking booking = bookingService.findById(bookingDTO.getId());

        if (booking == null) {
            booking = new Booking();
        }

        Account account = accountService.findById(bookingDTO.getAccountId());
        booking.setAccount(account);
        booking.setPrice(formatUtil.formatNumber(bookingDTO.getPrice()));
        booking.setDiscount(bookingDTO.getDiscount());
        booking.setProgress(bookingDTO.getProgress());
        booking.setRoomType(bookingDTO.getRoomType());
        booking.setIsConfirm(bookingDTO.getIsConfirm());
        if (!StringUtils.isEmpty(bookingDTO.getTimeInput())) {
            booking.setTimeOrder(DateUtil.convertStringToDate(bookingDTO.getTimeInput(), "MM/dd/yyyy h:mm a"));
        }
        if (!bookingDTO.getTotalBill().isEmpty()) {
            booking.setTotalBill(formatUtil.formatNumber(bookingDTO.getTotalBill()));
        }
        Karaoke karaoke = karaokeService.findById(bookingDTO.getOwnerId());
        booking.setOwner(karaoke);
        return booking;
    }

}
