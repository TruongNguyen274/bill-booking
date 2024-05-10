package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.ReportBookingDTO;
import vn.billbooking.utils.DateUtil;
import java.util.Date;


@Component
public class ReportValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ReportBookingDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            ReportBookingDTO bookingDTO = (ReportBookingDTO) target;

            if (bookingDTO.getStartDate() != null && !bookingDTO.getStartDate().trim().isEmpty() &&
                    bookingDTO.getEndDate() != null && !bookingDTO.getEndDate().trim().isEmpty()) {
                Date dateStart = DateUtil.convertStringToDate(bookingDTO.getStartDate(), "yyyy-MM-dd");
                Date dateEnd = DateUtil.convertStringToDate(bookingDTO.getEndDate(), "yyyy-MM-dd");
                int resultCheck = dateStart.compareTo(dateEnd);
                if (resultCheck > 0) {
                    errors.rejectValue("startDate", "Thời gian bắt đầu lớn hơn thời gian kết thúc", "Thời gian bắt đầu lớn hơn thời gian kết thúc");
                }
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
