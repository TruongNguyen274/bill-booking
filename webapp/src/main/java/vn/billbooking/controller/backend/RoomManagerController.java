package vn.billbooking.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.dto.PromotionDTO;
import vn.billbooking.model.dto.RoomDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Promotion;
import vn.billbooking.model.entity.Room;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.RoomMapper;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.RoomService;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.RoomManagerValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("quan-ly/phong")
public class RoomManagerController {

    private final String redirectUrl = "/quan-ly/phong/";
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
    private RoomManagerValidator roomManagerValidator;

    @GetMapping(value = {"", "/" })
    public String list(Model model, Authentication authentication) {
        try{
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<RoomDTO> roomDTOS = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                Karaoke karaoke = karaokeService.findByAccount(account).get(0);
                roomDTOS = roomMapper.toListDTO(roomService.findByKaraoke(karaoke));
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                roomDTOS = roomMapper.toListDTO(roomService.findAll());
            }
            model.addAttribute("roomList", roomDTOS);

            return "backend/room_manager_list";
        } catch (Exception ex) {
            ex.getMessage();
            return redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model, Authentication authentication) {
        try{
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Karaoke> karaokeList = new ArrayList<>();
            RoomDTO roomDTO = new RoomDTO();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                model.addAttribute("isOwner", "OWNER");
                roomDTO.setKaraokeId(karaokeList.get(0).getId());
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }
            model.addAttribute("roomDTO", roomDTO);
            model.addAttribute("errorList", new HashMap<>());
            model.addAttribute("karaokeList", karaokeList);

            return "backend/room_manager_form";
        } catch (Exception ex) {
            ex.getMessage();
            return redirectUrl;
        }
    }

    @GetMapping(value = { "/bieu-mau/{id}" })
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status, Authentication authentication) {
        try{
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Karaoke> karaokeList = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                model.addAttribute("isOwner", "OWNER");
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }

            Room room = roomService.findById(id);
            if (room == null) {
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("roomDTO",roomMapper.toDTO(room));
            model.addAttribute("errorList", new HashMap<>());
            model.addAttribute("karaokeList", karaokeList);

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/room_manager_form";
        } catch (Exception ex) {
            ex.getMessage();
            return redirectUrl;
        }
    }

     @PostMapping(value = "/bieu-mau/")
    public String save(Model model, RoomDTO roomDTO, BindingResult bindingResult, Authentication authentication) {
        String redirectUrl = "/quan-ly/phong/";
        Karaoke karaoke = karaokeService.findById(roomDTO.getKaraokeId());
        try {
            roomManagerValidator.validate(roomDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));

                Account account = ObjectUtil.getAccount(authentication);
                long roleId = 0;
                if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                    roleId = account.getRole().getId();
                }
                //role owner
                List<Karaoke> karaokeList = new ArrayList<>();
                if (roleId == ContantUtil.ROLE_OWNER) {
                    karaokeList = karaokeService.findByAccount(account);
                    model.addAttribute("isOwner", "OWNER");
                } else if (roleId == ContantUtil.ROLE_ADMIN) {
                    Karaoke karaokeTemp = new Karaoke();
                    karaokeTemp.setId(0L);
                    karaokeTemp.setName("Chọn Quán Karaoke");
                    karaokeList.add(karaokeTemp);
                    karaokeList.addAll(karaokeService.findAll());
                }
                model.addAttribute("karaokeList", karaokeList);
                return "/backend/room_manager_form";
            } else {
                Room room = roomMapper.toEntity(roomDTO);
                room.setKaraoke(karaoke);
                roomService.save(room);
                redirectUrl = "/quan-ly/phong/bieu-mau/"+ room.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }
}
