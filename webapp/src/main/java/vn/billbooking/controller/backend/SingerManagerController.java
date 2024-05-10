package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.dto.SingerDTO;
import vn.billbooking.model.entity.Singer;
import vn.billbooking.model.mapper.SingerMapper;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.service.SingerService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.SingerValidator;

import java.util.HashMap;

@Controller
@RequestMapping("/quan-ly/ca-si")
public class SingerManagerController {

    private static final String redirectUrl = "/quan-ly/ca-si/";

    @Autowired
    private SingerService singerService;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private SingerValidator singerValidator;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(value = {"", "/"})
    public String list(Model model, Authentication authentication) {
        try {
            model.addAttribute("singerList", singerMapper.toListDTO(singerService.findAll()));
            return "backend/singer_list";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model, Authentication authentication) {
        try {
            model.addAttribute("singerDTO", new SingerDTO());

            return "backend/singer_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }

    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status, Authentication authentication) {
        try {
            Singer singer = singerService.findById(id);
            if (singer == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("singerDTO", singerMapper.toDTO(singer));
            model.addAttribute("errorList", new HashMap<>());

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/singer_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, SingerDTO singerDTO, BindingResult bindingResult, Authentication authentication) {
        String redirectUrl = "/quan-ly/ca-si/";
        try {
            singerValidator.validate(singerDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("singerDTO", singerDTO);
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));

                return "/backend/singer_form";
            } else {
                Singer singer = singerMapper.toEntity(singerDTO);

                if (singerDTO.getAvatarMul() != null && !ObjectUtils.isEmpty(singerDTO.getAvatarMul().getOriginalFilename())) {
                    FileDTO fileDTOBack = fileUploadService.uploadFile(singerDTO.getAvatarMul());
                    singer.setAvatar(fileDTOBack.getPath());
                }

                singerService.save(singer);

                redirectUrl = "/quan-ly/ca-si/bieu-mau/" + singer.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }


}

