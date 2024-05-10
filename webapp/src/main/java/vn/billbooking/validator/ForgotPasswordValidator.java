package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.AccountDTO;

@Component
public class ForgotPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDTO accountDTO = (AccountDTO) target;

        // verify new password
        if(accountDTO.getNewPassword() == null || accountDTO.getNewPassword().trim().isEmpty()){
            errors.rejectValue("newPassword", "Vui lòng nhập mật khẩu mới!",
                    "Vui lòng nhập mật khẩu mới!");
        }else{
            if (accountDTO.getNewPassword().length() < 8) {
                errors.rejectValue("newPassword", "Mật khẩu cần ít nhất 8 ký tự!",
                        "Mật khẩu cần ít nhất 8 ký tự!");
            }
        }

        // verify new password again
        if (accountDTO.getVerifyNewPassword() == null || accountDTO.getVerifyNewPassword().trim().isEmpty()) {
            errors.rejectValue("verifyNewPassword", "Vui lòng nhập lại mật khẩu mới!",
                    "Vui lòng nhập lại mật khẩu mới!");
        } else {
            if (!accountDTO.getNewPassword().equalsIgnoreCase(accountDTO.getVerifyNewPassword())) {
                errors.rejectValue("verifyNewPassword", "Mật khẩu không trùng khớp!",
                        "Mật khẩu không trùng khớp!");
            }
        }
    }
}
