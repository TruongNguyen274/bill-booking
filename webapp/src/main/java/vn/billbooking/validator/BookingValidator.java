package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.BookingDTO;
import vn.billbooking.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Component
public class BookingValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BookingDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            BookingDTO bookingDTO = (BookingDTO) target;

            if (bookingDTO.getOwnerId() == 0) {
                errors.rejectValue("owner", "Vui lòng chọn Quán Karaoke", "Vui lòng chọn Quán Karaoke");
            }

            if (bookingDTO.getAccountId() == 0) {
                errors.rejectValue("account", "Vui lòng Nhập Tài Khoản", "Vui lòng Nhập Tài Khoản");
            }

            if (bookingDTO.getPrice() == null || bookingDTO.getPrice().trim().isEmpty()) {
                errors.rejectValue("price", "Vui lòng nhập Giá Phòng!",
                        "Vui lòng nhập Giá Phòng!");
            }

            //verify price
            if (bookingDTO.getRoomType() == null || bookingDTO.getRoomType().trim().isEmpty()) {
                errors.rejectValue("roomType", "Vui lòng chọn Loại Phòng!",
                        "Vui lòng chọn Loại Phòng!");
            }

            //tạm thời book là >= thời gian hiện tại, với không được book trước 7 ngày
            if (bookingDTO.getTimeInput() == null || bookingDTO.getTimeInput().trim().isEmpty()) {
                errors.rejectValue("timeInput", "Vui lòng Nhập Ngày Tháng", "Vui lòng Nhập Ngày Tháng");
            } else {
                //check timeBooking >= now
                Date date = DateUtil.convertStringToDate(bookingDTO.getTimeInput(), "MM/dd/yyyy hh:mm a");
                boolean checkDate = compareDate(date);
                if (checkDate == false) {
                    errors.rejectValue("timeInput", "Thời gian đặt phòng không hợp lệ vui lòng kiểm tra lại", "Thời gian đặt phòng không hợp lệ vui lòng kiểm tra lại");
                }
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    private boolean compareDate(Date dateInput) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        Date date = new Date();
        int checkResult = dateInput.compareTo(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        Date date7 = calendar.getTime();

        int checkDate7 = dateInput.compareTo(date7);
        if (checkResult >= 0 && checkDate7 <= 0) {
            return true;
        }
        return false;
    }
}
