package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.SingerDTO;

@Component
public class SingerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SingerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            SingerDTO singerDTO = (SingerDTO) target;

            // verify name
            if (singerDTO.getName() == null || singerDTO.getName().trim().isEmpty()) {
                errors.rejectValue("name", "Vui lòng nhập Tên Bài Viết!",
                        "Vui lòng nhập Tên Bài Viết!");
            }

            // verify description
            if (singerDTO.getDescription() == null || singerDTO.getDescription().trim().isEmpty()) {
                errors.rejectValue("description", "Vui lòng nhập Mô Tả Ngắn!",
                        "Vui lòng nhập Mô Tả Ngắn!");
            }

//            // verify avatar
//            if (singerDTO.getAvatarMul() == null || singerDTO.getAvatarMul().getOriginalFilename().isEmpty()) {
//                errors.rejectValue("avatar", "Vui lòng chọn Hình Ảnh!",
//                        "Vui lòng chọn Hình Ảnh!");
//            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
