package vn.billbooking.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.GalleryDTO;

@Component
public class GelleryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GalleryDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GalleryDTO galleryDTO = (GalleryDTO) target;

        // verify type
        if (galleryDTO.getType() == null || galleryDTO.getType().trim().isEmpty()) {
            errors.rejectValue("type", "Vui lòng chọn Loại Thư Viện!",
                    "Vui lòng chọn Loại Thư Viện!");
        }

        // verify title
        if (galleryDTO.getTitle() == null || galleryDTO.getTitle().trim().isEmpty()) {
            errors.rejectValue("title", "Vui lòng nhập Tiêu Đề!",
                    "Vui lòng nhập Tiêu Đề!");
        }

    }

}
