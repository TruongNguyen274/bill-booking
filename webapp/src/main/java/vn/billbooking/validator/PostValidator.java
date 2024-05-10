package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.PostDTO;

@Component
public class PostValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            PostDTO postDTO = (PostDTO) target;

            // verify name
            if (postDTO.getName() == null || postDTO.getName().trim().isEmpty()) {
                errors.rejectValue("name", "Vui lòng nhập Tên Bài Viết!",
                        "Vui lòng nhập Tên Bài Viết!");
            }

            // verify description
            if (postDTO.getDescription() == null || postDTO.getDescription().trim().isEmpty()) {
                errors.rejectValue("description", "Vui lòng nhập Mô Tả Ngắn!",
                        "Vui lòng nhập Mô Tả Ngắn!");
            }

            // verify owner
            if (postDTO.getOwnerId() == 0) {
                errors.rejectValue("owner", "Vui lòng chọn Quán Karaoke", "Vui lòng chọn Quán Karaoke");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
