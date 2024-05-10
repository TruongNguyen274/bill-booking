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
public class AccountValidator implements Validator  {

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

            // verify role
            if (accountDTO.getRoleId() == 0) {
                errors.rejectValue("role", "Vui lòng chọn Quyền Truy Cập!",
                        "Vui lòng chọn Quyền Truy Cập!");
            }

            // verify location
            if (accountDTO.getLocationId() == 0) {
                errors.rejectValue("location", "Vui lòng chọn Khu Vực!",
                        "Vui lòng chọn Khu Vực!");
            }

            // verify fullName
            if (ValidatorUtil.isEmpty(accountDTO.getFullName())) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ và Tên!",
                        "Vui lòng nhập Họ và Tên!");
            }

            // verify email
            String email = accountDTO.getEmail();
            if (ValidatorUtil.isEmpty(email)) {
                errors.rejectValue("email", "Vui lòng nhập Địa chỉ Email!",
                        "Vui lòng nhập Địa chỉ Email!");
            } else {
                account = accountService.findByEmail(email.trim());

                if (account != null && account.getId() != accountDTO.getId()) {
                    errors.rejectValue("email", "Địa chỉ Email đã được đăng ký!",
                            "Địa chỉ Email đã được đăng ký!");
                }
            }

            // verify phone
            String phone = accountDTO.getPhone();
            if (ValidatorUtil.isEmpty(phone)) {
                errors.rejectValue("phone", "Vui lòng nhập Số Điện Thoại!",
                        "Vui lòng nhập Số Điện Thoại!");
            } else {
                account = accountService.findByPhone(phone);

                if (account != null && account.getId() != accountDTO.getId()) {
                    errors.rejectValue("phone", "Số Điện Thoại đã được đăng ký!",
                            "Số Điện Thoại đã được đăng ký!");
                }
            }

            // verify password
            String password = accountDTO.getPassword();
            if (ValidatorUtil.isEmpty(password)) {
                errors.rejectValue("password", "Vui lòng nhập Mật Khẩu!",
                        "Vui lòng nhập Mật Khẩu!");
            } else {
                if (password.length() < 8 || password.length() > 32) {
                    errors.rejectValue("password", "Mật khẩu phải từ 8 đến 32 kí tự!",
                            "Mật khẩu phải từ 8 đến 32 kí tự!");
                } else {
                    if (!password.equalsIgnoreCase(accountDTO.getRepassword())) {
                        errors.rejectValue("password", "Xác nhận mật khẩu không đúng!",
                                "Xác nhận mật khẩu không đúng!");
                    }
                }
            }
        } catch (Exception e){
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
