package vn.billbooking.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.PromotionDTO;
import vn.billbooking.service.PromotionService;
import vn.billbooking.utils.DateUtil;
import vn.billbooking.utils.ValidatorUtil;

import java.util.Date;

@Component
public class PromotionValidator implements Validator {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ValidatorUtil validatorUtil;



    @Override
    public boolean supports(Class<?> clazz) {
        return PromotionDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PromotionDTO promotionDTO = (PromotionDTO) target;

        // verify name
        if (promotionDTO.getName() == null || promotionDTO.getName().trim().isEmpty()) {
            errors.rejectValue("name", "Vui lòng nhập Tên Mã Giảm Giá!",
                    "Vui lòng nhập Tên Mã Giảm Giá!");
        }

        // verify code
        if (promotionDTO.getCode() == null || promotionDTO.getCode().trim().isEmpty()) {
            errors.rejectValue("code", "Vui lòng nhập Mã Giảm Giá!",
                    "Vui lòng nhập Mã Giảm Giá!");
        }

        // verify discount
        if (promotionDTO.getDiscount() == null || promotionDTO.getDiscount().trim().isEmpty()) {
            errors.rejectValue("discount", "Vui lòng nhập Số Tiền Giảm!",
                    "Vui lòng nhập Số Tiền Giảm!");
        }

        // verify description
        if (promotionDTO.getDescription() == null || promotionDTO.getDescription().trim().isEmpty()) {
            errors.rejectValue("description", "Vui lòng nhập Mô Tả!",
                    "Vui lòng nhập Mô Tả!");
        }

        // verify startDate --Startdate >= ngay hien tại, enddate <= 30 so vs ngày hien tại nha
        Date startDate = null;
        boolean checkStartDate = false;
        boolean checkStartIsNull = false;
        if (promotionDTO.getStartDate() == null || promotionDTO.getStartDate().trim().isEmpty()) {
            errors.rejectValue("startDate", "Vui lòng nhập Ngày Bắt Đầu!",
                    "Vui lòng nhập Ngày Bắt Đầu!");
            checkStartIsNull = true;
        } else {
            startDate = DateUtil.convertStringToDate(promotionDTO.getStartDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            checkStartDate = DateUtil.compareStartDate(startDate);
            if (checkStartDate == false) {
                errors.rejectValue("startDate", "Thời gian không hợp lệ vui lòng kiểm tra lại!", "Thời gian không hợp lệ vui lòng kiểm tra lại!");
            }
        }

        // verify endDate
        Date endDate = null;
        boolean checkEndDate = false;
        boolean checkEndIsNull = false;
        if (promotionDTO.getEndDate() == null || promotionDTO.getEndDate().trim().isEmpty()) {
            errors.rejectValue("endDate", "Vui lòng nhập Ngày Kết Thúc!",
                    "Vui lòng nhập Ngày Kết Thúc!");
            checkEndIsNull =true;

        } else {
            endDate = DateUtil.convertStringToDate(promotionDTO.getEndDate(), "yyyy-MM-dd");
            checkEndDate = DateUtil.compareEndDate(endDate);
            if (checkEndDate == false) {
                errors.rejectValue("endDate", "Thời gian không hợp lệ vui lòng kiểm tra lại!", "Thời gian không hợp lệ vui lòng kiểm tra lại!");
            }
        }

        if (!checkStartIsNull && !checkEndIsNull && !DateUtil.compareStartDateEndDate(startDate, endDate) && checkStartDate && checkEndDate){
            errors.rejectValue("startDate", "Ngày bắt đầu không được lớn hơn ngày kết thúc!", "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            errors.rejectValue("endDate", "Ngày kết thúc không được nhỏ hơn ngày bắt đầu!", "Ngày kết thúc không được nhỏ hơn ngày bắt đầu!");
        }
    }

}
