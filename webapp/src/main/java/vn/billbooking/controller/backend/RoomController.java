package vn.billbooking.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.dto.RoomDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Room;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.RoomMapper;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.RoomService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.RoomValidator;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("quan-ly/quan-karaoke")
public class RoomController {
    private final String redirectUrl = "/quan-ly/quan-karaoke/";
    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private RoomValidator roomValidator;

    @GetMapping(value = {"/{idQuan}/phong"})
    public String list(Model model, @PathVariable long idQuan) {
        try {
            Karaoke karaoke = karaokeService.findById(idQuan);
            if (karaoke == null) {
                return "redirect:" + redirectUrl;
            }
            List<Room> rooms = roomService.findByKaraoke(karaoke);
            List<RoomDTO> roomDTOS = roomMapper.toListDTO(rooms);
            model.addAttribute("roomList", roomDTOS);
            model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));

            return "backend/room_list";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/{idQuan}/phong/bieu-mau"})
    public String create(Model model, @PathVariable long idQuan) {
        try {
            Karaoke karaoke = karaokeService.findById(idQuan);
            model.addAttribute("roomDTO", new RoomDTO());
            model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));
            model.addAttribute("errorList", new HashMap<>());

            return "backend/room_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/{idQuan}/phong/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @PathVariable long idQuan, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Room room = roomService.findById(id);
            Karaoke karaoke = karaokeService.findById(idQuan);

            if (room == null) {
                String redirectUrl = "/quan-ly/quan-karaoke/" + idQuan + "/phong";
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("roomDTO", roomMapper.toDTO(room));
            model.addAttribute("errorList", new HashMap<>());
            model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/room_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/phong/bieu-mau/")
    public String save(Model model, RoomDTO roomDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/quan-karaoke/" + roomDTO.getKaraokeId() + "/phong/";
        try {
            Karaoke karaoke = karaokeService.findById(roomDTO.getKaraokeId());
            roomValidator.validate(roomDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "/backend/room_form";
            } else {
                Room room = roomMapper.toEntity(roomDTO);
                room.setKaraoke(karaoke);
                roomService.save(room);

                redirectUrl = "/quan-ly/quan-karaoke/" + roomDTO.getKaraokeId() + "/phong/bieu-mau/" + room.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }


}
