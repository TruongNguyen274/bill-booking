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
import vn.billbooking.model.dto.PostDTO;
import vn.billbooking.model.dto.PromotionDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Post;
import vn.billbooking.model.entity.Promotion;
import vn.billbooking.model.mapper.PromotionMapper;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.PromotionService;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.PromotionValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quan-ly/giam-gia")
public class PromotionManagerController {

    private final String redirectUrl = "/quan-ly/giam-gia/";

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private PromotionValidator promotionValidator;

    @Autowired
    private ValidatorUtil validatorUtil;

    @GetMapping(value = {"", "/"})
    public String index(Model model, Authentication authentication) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Promotion> promotions = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                Karaoke karaoke = karaokeService.findByAccount(account).get(0);
                promotions = promotionService.findByOwner(karaoke);
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                promotions = promotionService.findAll();
            }
            model.addAttribute("promotionList", promotionMapper.toListDTO(promotions));
            return "backend/promotion_list";
        } catch (Exception ex) {
            ex.getMessage();
            return redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model, Authentication authentication) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Karaoke> karaokeList = new ArrayList<>();
            PromotionDTO promotionDTO = new PromotionDTO();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                model.addAttribute("isOwner", "OWNER");
                promotionDTO.setOwnerId(karaokeList.get(0).getId());
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }

            model.addAttribute("promotionDTO", promotionDTO);
            model.addAttribute("karaokeList", karaokeList);
            return "backend/promotion_form";
        } catch (Exception ex) {
            ex.getMessage();
            return redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status, Authentication authentication) {
        try {
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

            Promotion promotion = promotionService.findById(id);
            if (promotion == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("promotionDTO", promotionMapper.toDTO(promotion));
            model.addAttribute("karaokeList", karaokeList);
            model.addAttribute("errorList", new HashMap<>());

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/promotion_form";
        } catch (Exception ex) {
            ex.getMessage();
            return redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, PromotionDTO promotionDTO, BindingResult bindingResult, Authentication authentication) {
        String redirectUrl = "/quan-ly/giam-gia/";
        try {
            promotionValidator.validate(promotionDTO, bindingResult);

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
                    Karaoke karaoke = new Karaoke();
                    karaoke.setId(0L);
                    karaoke.setName("Chọn Quán Karaoke");
                    karaokeList.add(karaoke);
                    karaokeList.addAll(karaokeService.findAll());
                }
                model.addAttribute("karaokeList", karaokeList);
                return "/backend/promotion_form";
            } else {
                Promotion promotion = promotionMapper.toEntity(promotionDTO);

                // upload file
                if (promotionDTO.getAvatarMul() != null && !ObjectUtils.isEmpty(promotionDTO.getAvatarMul().getOriginalFilename())) {
                    FileDTO fileDTOFront = fileUploadService.uploadFile(promotionDTO.getAvatarMul());
                    promotion.setAvatar(fileDTOFront.getPath());
                }

                promotionService.save(promotion);

                redirectUrl = "/quan-ly/giam-gia/bieu-mau/" + promotionDTO.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

}
