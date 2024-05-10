package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.CommentDTO;

@Component
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CommentDTO commentDTO = (CommentDTO) target;

        // verify reply
        if (commentDTO.getReply() == null || commentDTO.getReply().trim().isEmpty()) {
            errors.rejectValue("reply", "Vui lòng nhập nội dung!",
                    "Vui lòng nhập nội dung!");
        }
    }
}
