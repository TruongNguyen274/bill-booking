package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.*;
import vn.billbooking.model.entity.*;
import vn.billbooking.model.mapper.KaraokeImageMapper;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.service.*;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.KaraokeValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quan-ly/quan-karaoke")
public class KaraokeManagerController {
    private final String redirectUrl = "/quan-ly/quan-karaoke/";
    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private KaraokeValidator karaokeValidator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private KaraokeImageMapper karaokeImageMapper;

    @Autowired
    private KaraokeImageService karaokeImageService;

    @Autowired
    private PointService pointService;

    @GetMapping(value = {"", "/" })
    public String list(Model model) {
        try {
            List<Karaoke> karaokes = karaokeService.findAll();
            List<KaraokeDTO> karaokeDTOS = karaokeMapper.toListDTO(karaokes);
            model.addAttribute("karaokeList", karaokeDTOS);

            return "backend/karaoke_list";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model) {
        try {
            setModelAttribute(model, new KaraokeDTO(), true);
            return "backend/karaoke_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/doi-khu-vuc/{id}"})
    public String changeLocation(Model model, @PathVariable long id, @RequestParam(required = false) long locationId) {
        try {
            Karaoke karaoke = karaokeService.findById(id);
            if (karaoke == null) {
                return "redirect:" + redirectUrl;
            }

            KaraokeDTO karaokeDTO = karaokeMapper.toDTO(karaoke);
            karaokeDTO.setLocationId(locationId);

            List<KaraokeImage> karaokeImages = karaokeImageService.findByKaraoke(karaoke);
            List<KaraokeImageDTO> karaokeImageDTOList = karaokeImageMapper.toListDTO(karaokeImages);
            model.addAttribute("karaokeImageDTOList", karaokeImageDTOList);
            setModelAttribute(model, karaokeDTO, false);
            model.addAttribute("messageDTO", null);
            return "backend/karaoke_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Karaoke karaoke = karaokeService.findById(id);
            if (karaoke == null) {
                return "redirect:" + redirectUrl;
            }

            List<KaraokeImage> karaokeImages = karaokeImageService.findByKaraoke(karaoke);
            List<KaraokeImageDTO> karaokeImageDTOList = karaokeImageMapper.toListDTO(karaokeImages);
            model.addAttribute("karaokeImageDTOList", karaokeImageDTOList);
            setModelAttribute(model, karaokeMapper.toDTO(karaoke), false);

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/karaoke_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, KaraokeDTO karaokeDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/quan-karaoke/";

        try {
            karaokeValidator.validate(karaokeDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                setModelAttribute(model, karaokeDTO, false);

                return "/backend/karaoke_form";
            } else {
                Karaoke karaoke = karaokeMapper.toEntity(karaokeDTO);

                if (karaokeDTO.getAvatarMul() != null && !ObjectUtils.isEmpty(karaokeDTO.getAvatarMul().getOriginalFilename())) {
                    FileDTO fileDTOBack = fileUploadService.uploadFile(karaokeDTO.getAvatarMul());
                    karaoke.setAvatar(fileDTOBack.getPath());
                }

                karaokeService.save(karaoke);
                redirectUrl = "/quan-ly/quan-karaoke/bieu-mau/" + karaoke.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/hinh-anh/")
    public String uploadImage(Model model, KaraokeFileDTO karaokeFileDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/quan-karaoke/";
        try {
            List<FileDTO> fileDTOList = fileUploadService.uploadMutilFile(karaokeFileDTO.getFiles());
            if (fileDTOList != null) {
                karaokeImageService.saveAll(karaokeFileDTO.getId(), fileDTOList);
            }
            redirectUrl = "/quan-ly/quan-karaoke/bieu-mau/" + karaokeFileDTO.getId();
            return "redirect:" + redirectUrl;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/delete-image/{id}"})
    public String delete(Model model, @PathVariable long id) {
        try {
            String redirectUrl = "";
            KaraokeImage karaokeImage = karaokeImageService.findById(id);
            karaokeImageService.delete(id);
            redirectUrl = "/quan-ly/quan-karaoke/bieu-mau/" + karaokeImage.getKaraoke().getId() + "?action=save&status=success";

            return "redirect:" + redirectUrl;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    public void setModelAttribute(Model model, KaraokeDTO karaokeDTO, boolean isNew) {
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();
        account.setId(0L);
        account.setFullName("Chọn Khách Hàng");
        accounts.add(account);
        if (isNew) {
            accounts.addAll(accountService.getOwnerActiveKaraoke());
        } else {
            accounts.addAll(accountService.findByRoleAndStatusIsTrue(roleService.findById(ContantUtil.ROLE_OWNER)));
        }
        model.addAttribute("accountList", accounts);

        KaraokeFileDTO karaokeFileDTO = new KaraokeFileDTO();
        karaokeFileDTO.setId(karaokeDTO.getId());

        List<Location> locations = new ArrayList<>();
        Location location = new Location();
        location.setId(0L);
        location.setName("Chọn Khu Vực");
        locations.add(location);
        locations.addAll(locationService.findAll());

        model.addAttribute("locationList", locations);
        model.addAttribute("karaokeDTO", karaokeDTO);
        model.addAttribute("errorList", new HashMap<>());
        model.addAttribute("karaokeFileDTO", karaokeFileDTO);

        List<Point> points = new ArrayList<>();
        Point point = new Point();
        point.setId(0L);
        point.setName("Chọn Vị Trí");
        points.add(point);
        points.addAll(pointService.findByLocation(locationService.findById(karaokeDTO.getLocationId())));
        model.addAttribute("pointList", points);
    }

}
