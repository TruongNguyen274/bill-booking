package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.CheckoutDTO;
import vn.billbooking.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CheckoutValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CheckoutDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            CheckoutDTO checkoutDTO = (CheckoutDTO) target;

            // verify fullName
            if (checkoutDTO.getFullName() == null || checkoutDTO.getFullName().trim().isEmpty()) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ Và Tên",
                        "Vui lòng nhập Họ Và Tên");
            }

            // verify owner
            if (checkoutDTO.getOwnerId() == 0) {
                errors.rejectValue("owner", "Vui lòng chọn Quán Karaoke",
                        "Vui lòng chọn Quán Karaoke");
            }

            // verify phone
            if (checkoutDTO.getPhone() == null || checkoutDTO.getPhone().trim().isEmpty()) {
                errors.rejectValue("phone", "Vui lòng nhập Số Điện Thoại!",
                        "Vui lòng nhập Số Điện Thoại!");
            }

            // verify room
            if (checkoutDTO.getRoomType() == null || checkoutDTO.getRoomType().trim().isEmpty()) {
                errors.rejectValue("roomType", "Vui lòng chọn Loại Phòng!",
                        "Vui lòng chọn Loại Phòng!");
            }

            // verify people
            if (checkoutDTO.getRoomType() == null || checkoutDTO.getRoomType().trim().isEmpty()) {
                errors.rejectValue("totalPeople", "Vui lòng chọn Số Người Tham Gia!",
                        "Vui lòng chọn Số Người Tham Gia!");
            }

            // tạm thời book là >= thời gian hiện tại, với không được book trước 7 ngày
            if (checkoutDTO.getTimeOrder() == null || checkoutDTO.getTimeOrder().trim().isEmpty()) {
                errors.rejectValue("timeOrder", "Vui lòng chọn Ngày Đặt Phòng",
                        "Vui lòng chọn Ngày Đặt Phòng");
            } else {
                // check timeBooking >= now
//                Date date = DateUtil.convertStringToDate(checkoutDTO.getTimeOrder(), "yyyy-MM-ddTHH:ss");
//                boolean checkDate = compareDate(date);
//                if (checkDate == false) {
//                    errors.rejectValue("timeOrder", "Thời gian đặt phòng không hợp lệ",
//                            "Thời gian đặt phòng không hợp lệ");
//                }
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
