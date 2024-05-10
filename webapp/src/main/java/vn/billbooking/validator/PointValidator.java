package vn.billbooking.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.PointDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PointService;

@Component
public class PointValidator implements Validator {

    @Autowired
    private LocationService locationService;

    @Autowired
    PointService pointService;


    @Override
    public boolean supports(Class<?> clazz) {
        return PointDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            PointDTO pointDTO = (PointDTO) target;


            // verify name
            if (pointDTO.getName() == null || pointDTO.getName().trim().isEmpty()) {
                errors.rejectValue("name", "Vui lòng nhập Tên Vị trí!",
                        "Vui lòng nhập Tên Vị trí!");
            } else {
                // check name duplicate
                Location location = locationService.findById(pointDTO.getLocationId());
                Point point = pointService.findByLocationAndName(location, pointDTO.getName());
                if (point != null && pointDTO.getName().equals(point.getName()) && point.getId() != pointDTO.getId()) {
                    errors.rejectValue("name", "Tên Vị trí bị trùng vui lòng nhập lại!",
                            "Tên Vị trí bị trùng vui lòng nhập lại!");
                }
            }
        } catch (Exception e){
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
