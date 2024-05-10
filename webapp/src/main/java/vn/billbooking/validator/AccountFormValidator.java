package vn.billbooking.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.service.AccountService;
import vn.billbooking.utils.ValidatorUtil;


@Component
public class AccountFormValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            AccountDTO accountDTO = (AccountDTO) target;
            Account account = null;

            // verify fullName
            if (ValidatorUtil.isEmpty(accountDTO.getFullName())) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ và Tên!",
                        "Vui lòng nhập Họ và Tên!");
            }

            // verify phone
            String phone = accountDTO.getPhone();
            if (ValidatorUtil.isEmpty(phone)) {
                errors.rejectValue("phone", "Vui lòng nhập Số Điện Thoại!",
                        "Vui lòng nhập Số Điện Thoại!");
            } else {
                if (!ValidatorUtil.checkPhone(phone)) {
                    errors.rejectValue("phone", "Vui lòng nhập đúng định dạng", "Vui lòng nhập đúng định dạng");
                } else {
                    account = accountService.findByPhone(phone);
                    if (account != null && account.getId() != accountDTO.getId()) {
                        errors.rejectValue("phone", "Số Điện Thoại đã được đăng ký!",
                                "Số Điện Thoại đã được đăng ký!");
                    }
                }
            }

            // verify location
            if (accountDTO.getLocationId() == 0) {
                errors.rejectValue("location", "Vui lòng chọn Khu Vực!",
                        "Vui lòng chọn Khu Vực!");
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}


