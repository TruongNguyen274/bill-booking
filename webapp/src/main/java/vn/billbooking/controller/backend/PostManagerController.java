package vn.billbooking.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.*;
import vn.billbooking.model.entity.*;
import vn.billbooking.model.mapper.PostMapper;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.PostService;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.PostValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("quan-ly/bai-viet")
public class PostManagerController {

    private static final String redirectUrl = "/quan-ly/bai-viet/";
    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private PostValidator postValidator;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(value = {"", "/"})
    public String list(Model model, Authentication authentication) {
        try {
            Account account = ObjectUtil.getAccount(authentication);
            long roleId = 0;
            if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(account.getRole())) {
                roleId = account.getRole().getId();
            }
            //role owner
            List<Post> posts = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                Karaoke karaoke = karaokeService.findByAccount(account).get(0);
                posts = postService.findByOwner(karaoke);
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                posts = postService.findAll();
            }
            model.addAttribute("postList", postMapper.toListDTO(posts));

            return "backend/post_list";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
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
            PostDTO postDTO = new PostDTO();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                model.addAttribute("isOwner", "OWNER");
                postDTO.setOwnerId(karaokeList.get(0).getId());
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }
            model.addAttribute("postDTO", postDTO);
            model.addAttribute("karaokeList", karaokeList);

            return "backend/post_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
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

            Post post = postService.findById(id);
            if (post == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("postDTO", postMapper.toDTO(post));
            model.addAttribute("errorList", new HashMap<>());
            model.addAttribute("karaokeList", karaokeList);

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/post_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, PostDTO postDTO, BindingResult bindingResult, Authentication authentication) {
        String redirectUrl = "/quan-ly/bai-viet/";
        try {
            postValidator.validate(postDTO, bindingResult);

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

                return "/backend/post_form";
            } else {
                Post post = postMapper.toEntity(postDTO);

                if (postDTO.getAvatarMul() != null && !ObjectUtils.isEmpty(postDTO.getAvatarMul().getOriginalFilename())) {
                    FileDTO fileDTOBack = fileUploadService.uploadFile(postDTO.getAvatarMul());
                    post.setAvatar(fileDTOBack.getPath());
                }

                postService.save(post);

                redirectUrl = "/quan-ly/bai-viet/bieu-mau/" + post.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }


}

