package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.CheckoutDTO;
import vn.billbooking.model.dto.EmailTemplateDTO;
import vn.billbooking.model.dto.ReportBookingDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Role;
import vn.billbooking.repository.BookingRepository;
import vn.billbooking.service.*;
import vn.billbooking.utils.DateUtil;
import vn.billbooking.utils.RandomUtil;
import vn.billbooking.utils.ValidatorUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findByOwner(Karaoke owner) {
        return bookingRepository.findByOwner(owner);
    }

    @Override
    public List<Booking> findByProgress(String progress) {
        return bookingRepository.findByProgress(progress);
    }

    @Override
    public List<Booking> findByOwnerAndProgress(Karaoke owner, String progress) {
        return bookingRepository.findByOwnerAndProgress(owner, progress);
    }

    @Override
    public Booking save(CheckoutDTO checkoutDTO) {
        try {
            Karaoke karaoke = karaokeService.findById(checkoutDTO.getOwnerId());

            // account
            Account account = null;
            if (checkoutDTO.getAccountId() > 0) {
                account = accountService.findById(checkoutDTO.getAccountId());
            }

            if (account == null && checkoutDTO.getEmail() != null && !checkoutDTO.getEmail().isEmpty()) {
                account = accountService.findByEmail(checkoutDTO.getEmail());
            }

            if (account == null && checkoutDTO.getPhone() != null && !checkoutDTO.getPhone().isEmpty()) {
                account = accountService.findByPhone(checkoutDTO.getPhone());
            }

            if (account == null) {
                AccountDTO accountDTO = new AccountDTO();
                if (checkoutDTO.getEmail() != null) {
                    accountDTO.setUsername(checkoutDTO.getEmail().trim());
                } else {
                    accountDTO.setUsername(checkoutDTO.getPhone().trim());
                }
                accountDTO.setFullName(checkoutDTO.getFullName());
                accountDTO.setEmail(checkoutDTO.getEmail());
                accountDTO.setPhone(checkoutDTO.getPhone());
                account = accountService.save(accountDTO, "USER");
            }

            Date timeOrder = convertToDate(checkoutDTO.getTimeOrder());

            // save
            Booking booking = new Booking();
            booking.setOwner(karaoke);
            booking.setAccount(account);
            booking.setTimeOrder(timeOrder);
            booking.setRoomType(checkoutDTO.getRoomType());
            booking.setTotalBill(0.0);
            booking.setTotalPeople(checkoutDTO.getTotalPeople());
            booking.setPrice(0.0);
            booking.setDiscount("");
            booking.setProgress("PENDING");
            booking.setNote(checkoutDTO.getNote());
            booking.setStatus(true);
            save(booking);

            // set code id
            String codeId = RandomUtil.generateId(booking.getId());
            booking.setCode("BK" + codeId);
            save(booking);

            List<String> toEmails = new ArrayList<>();

            // send mail to user
            String email = account.getEmail();
            if (!ValidatorUtil.isEmpty(email)) {
                toEmails.add(email);
            }

            // send email to admin
            Role role = roleService.findByName("ADMIN");
            List<Account> accountAdminList = accountService.findByRoleAndStatusIsTrue(role);

            if (accountAdminList != null) {
                for (Account acc : accountAdminList) {
                    if (!ValidatorUtil.isEmpty(acc.getEmail())) {
                        toEmails.add(acc.getEmail());
                    }
                }
            }

            // send email to owner
            if (!ValidatorUtil.isEmpty(karaoke.getAccount().getEmail())) {
                toEmails.add(karaoke.getAccount().getEmail());
            }

            String[] arrayToEmail = toEmails.toArray(String[]::new);

            String accountInfo = "";
            if (account.getPhone() != null && !account.getPhone().isEmpty()) {
                accountInfo = account.getPhone();
            }

            if (account.getEmail() != null && !account.getEmail().isEmpty()) {
                accountInfo = accountInfo + " - " +  account.getEmail();
            }

            // send email
            Map<String, Object> properties = new HashMap<>();
            properties.put("fullname", account.getFullName());
            properties.put("account", accountInfo);
            properties.put("karaokeCode", booking.getCode());
            properties.put("karaokeName", karaoke.getName() + " - " + karaoke.getPhone()  + " - " + karaoke.getAddress());
            properties.put("karaokeTime", DateUtil.convertDateToString(booking.getTimeOrder(), "HH:mm dd/MM/yyyy"));
            properties.put("karaokeRoom", booking.getRoomType());
            properties.put("karaokePeople", booking.getTotalPeople());
            properties.put("karaokeNote", booking.getNote());
            properties.put("emailContact", "Email: info@billbooking.vn");
            properties.put("phoneContact", "Phone: 1900 3118");

            EmailTemplateDTO emailTemplateDTO = new EmailTemplateDTO();
            emailTemplateDTO.setTo(arrayToEmail);
            emailTemplateDTO.setSubject("Đặt phòng " + karaoke.getName().toUpperCase() + " tại www.billbooking.vn");
            emailTemplateDTO.setContent("");
            emailTemplateDTO.setProperties(properties);

            emailService.sendEmailForCheckout(emailTemplateDTO);

            return booking;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Booking> findByAccount(Account account) {
        return bookingRepository.findByAccount(account);
    }

    @Override
    public Booking findByCode(String orderId) {
        return bookingRepository.findByCode(orderId);
    }

    @Override
    public List<Booking> findReportByOwner(ReportBookingDTO reportBookingDTO) {
        long karaokeId = reportBookingDTO.getKaraokeId();
        String startDate = reportBookingDTO.getStartDate();
        String endDate = reportBookingDTO.getEndDate();
        return bookingRepository.findReportByOwner(karaokeId, startDate, endDate);
    }

    @Override
    public List<Booking> findReport(ReportBookingDTO reportBookingDTO) {
        String startDate = reportBookingDTO.getStartDate();
        String endDate = reportBookingDTO.getEndDate();
        return bookingRepository.findReport(startDate, endDate);
    }

    public Date convertToDate(String text) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date timeOrder = null;
        try {
            timeOrder = formatter.parse(text);
        } catch (ParseException e) {
            return null;
        }
        return timeOrder;
    }

}
