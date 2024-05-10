package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.*;
import vn.billbooking.model.entity.Gallery;
import vn.billbooking.model.mapper.GalleryMapper;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.service.GalleryService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.GelleryValidator;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("quan-ly/thu-vien")
public class GalleryManagerController {
    private final String redirectUrl = "/quan-ly/thu-vien/bieu-mau";
    @Autowired
    private GalleryService galleryService;

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private GelleryValidator gelleryValidator;

    @Autowired
    private ValidatorUtil validatorUtil;

    @GetMapping(value = {"/", ""})
    public String list(Model model) {
        try {
            List<Gallery> galleries = galleryService.findAll();
            List<GalleryDTO> galleryDTOS = galleryMapper.toListDTO(galleries);
            model.addAttribute("galleryList", galleryDTOS);

            return "backend/gallery_list";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model) {
        try {
            Gallery gallery = new Gallery();
            gallery.setId(0L);
            model.addAttribute("galleryDTO", gallery);
            model.addAttribute("errorList", new HashMap<>());
            return "backend/gallery_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Gallery gallery = galleryService.findById(id);
            if (gallery == null) {
                String redirectUrl = "/quan-ly/thu-vien";
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("galleryDTO", galleryMapper.toDTO(gallery));
            model.addAttribute("errorList", new HashMap<>());

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/gallery_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, GalleryDTO galleryDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/thu-vien/bieu-mau";

        try {
            gelleryValidator.validate(galleryDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "/backend/gallery_form";
            } else {
                Gallery gallery = galleryMapper.toEntity(galleryDTO);

                if (galleryDTO.getImageMul() != null && !ObjectUtils.isEmpty(galleryDTO.getImageMul().getOriginalFilename())) {
                    FileDTO fileDTOBack = fileUploadService.uploadFile(galleryDTO.getImageMul());
                    gallery.setImage(fileDTOBack.getPath());
                }

                galleryService.save(gallery);
                redirectUrl = "/quan-ly/thu-vien/bieu-mau/" + gallery.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

}