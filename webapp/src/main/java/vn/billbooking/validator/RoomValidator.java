package vn.billbooking.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vn.billbooking.model.dto.RoomDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Room;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.RoomService;


@Component
public class RoomValidator implements Validator {

    @Autowired
    private RoomService roomService;

    @Autowired
    private KaraokeService karaokeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RoomDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            RoomDTO roomDTO = (RoomDTO) target;
            Karaoke karaoke = karaokeService.findById(roomDTO.getKaraokeId());
            Room room = roomService.findByKaraokeAndName(karaoke, roomDTO.getName());

            // verify name
            if (roomDTO.getName() == null || roomDTO.getName().trim().isEmpty()) {
                errors.rejectValue("name", "Vui lòng nhập Tên Phòng!",
                        "Vui lòng nhập Tên Phòng!");
            } else {
                if (roomDTO.getName().equals(room.getName()) && roomDTO.getId() != room.getId()) {
                    errors.rejectValue("name", "Tên phòng đã tồn tại!",
                            "Tên phòng đã tồn tại!");
                }
            }

            // verify regular price
            if (roomDTO.getRegularPrice() == null || roomDTO.getRegularPrice().trim().isEmpty()) {
                errors.rejectValue("regularPrice", "Vui lòng nhập Giá phòng!",
                        "Vui lòng nhập Giá phòng!");
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
