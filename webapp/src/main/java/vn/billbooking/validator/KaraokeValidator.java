package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.KaraokeDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class KaraokeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return KaraokeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            KaraokeDTO karaokeDTO = (KaraokeDTO) target;

            // verify name
            if (karaokeDTO.getName() == null || karaokeDTO.getName().trim().isEmpty()) {
                errors.rejectValue("name", "Vui lòng nhập Tên Quán!",
                        "Vui lòng nhập Tên Quán!");
            }

            // verify owner
            if (karaokeDTO.getAccountId() == 0) {
                errors.rejectValue("accountDTO", "Vui lòng chọn Quán Karaoke", "Vui lòng chọn Quán Karaoke");
            }

            // location
            if (karaokeDTO.getLocationId() == 0) {
                errors.rejectValue("locationDTO", "Vui lòng chọn khu vực", "Vui lòng chọn khu vực");
            }

            // verify address
            if (karaokeDTO.getAddress() == null || karaokeDTO.getAddress().trim().isEmpty()) {
                errors.rejectValue("address", "Vui lòng nhập Địa chỉ!",
                        "Vui lòng nhập Địa chỉ!");
            }

            // verify phone
            if (karaokeDTO.getPhone() == null || karaokeDTO.getPhone().trim().isEmpty()) {
                errors.rejectValue("phone", "Vui lòng nhập Số Điện Thoại!",
                        "Vui lòng nhập Số Điện Thoại!");
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public boolean checkPhone(String phoneNumber) {
        String regexStr = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


}
