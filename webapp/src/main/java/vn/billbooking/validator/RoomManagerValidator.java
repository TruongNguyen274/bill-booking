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
public class RoomManagerValidator implements Validator {

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

            //verify karaoke
            if (roomDTO.getKaraokeId() == 0) {
                errors.rejectValue("karaoke", "Vui lòng chọn Quán", "Vui lòng chọn Quán");
            } else {
                Karaoke karaoke = karaokeService.findById(roomDTO.getKaraokeId());
                Room room = roomService.findByKaraokeAndName(karaoke, roomDTO.getName());

                // verify name
                if (roomDTO.getName() == null || roomDTO.getName().trim().isEmpty()) {
                    errors.rejectValue("name", "Vui lòng nhập Tên Phòng!",
                            "Vui lòng nhập Tên Phòng!");
                } else {
                    if (room != null && roomDTO.getName().equals(room.getName()) && roomDTO.getId() != room.getId()) {
                        errors.rejectValue("name", "Tên phòng đã tồn tại!",
                                "Tên phòng đã tồn tại!");
                    }
                }
            }

            if (roomDTO.getName() == null || roomDTO.getName().trim().isEmpty() && roomDTO.getKaraokeId() == 0 ) {
                errors.rejectValue("name", "Vui lòng nhập Tên Phòng!",
                        "Vui lòng nhập Tên Phòng!");
            }

            //verify price
            if (roomDTO.getRegularPrice() == null || roomDTO.getRegularPrice().trim().isEmpty() ) {
                errors.rejectValue("regularPrice", "Vui lòng nhập Giá phòng!",
                        "Vui lòng nhập Giá phòng!");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

}
