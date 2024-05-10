package vn.billbooking.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.service.LocationService;

@Component
public class LocationValidator implements Validator {

    @Autowired
    private LocationService locationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return LocationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            LocationDTO locationDTO = (LocationDTO) target;
            Location location = locationService.findByName(locationDTO.getName());

            // verify name
            if (locationDTO.getName() == null || locationDTO.getName().trim().isEmpty()) {
                errors.rejectValue("name", "Vui lòng nhập Tên Khu Vực!",
                        "Vui lòng nhập Tên Khu Vực!");
            } else {
                // check name duplicate
                if (location != null && location.getId() != locationDTO.getId()) {
                    errors.rejectValue("name", "Tên Khu Vực bị trùng vui lòng nhập lại!",
                            "Tên Khu Vực bị trùng vui lòng nhập lại!");
                }
            }
        } catch (Exception e){
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
