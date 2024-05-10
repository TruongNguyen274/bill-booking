package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.CommentDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.dto.PostDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Comment;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Post;
import vn.billbooking.model.mapper.CommentMapper;
import vn.billbooking.service.CommentService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.utils.ContantUtil;
import vn.billbooking.utils.ObjectUtil;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.CommentValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quan-ly/binh-luan")
public class CommentManagerController {
    private final String redirectUrl = "/quan-ly/binh-luan/";
    @Autowired
    private CommentService commentService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentValidator commentValidator;

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
            List<Comment> comments = new ArrayList<>();
            if (roleId == ContantUtil.ROLE_OWNER) {
                Karaoke karaoke = karaokeService.findByAccount(account).get(0);
                comments = commentService.findByKaraoke(karaoke);
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                comments = commentService.findAll();
            }

            model.addAttribute("commentList", commentMapper.toListDTO(comments));
            return "backend/comment_list";
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
            CommentDTO commentDTO = new CommentDTO();
            if (roleId == ContantUtil.ROLE_OWNER) {
                karaokeList = karaokeService.findByAccount(account);
                model.addAttribute("isOwner", "OWNER");
                commentDTO.setOwnerId(karaokeList.get(0).getId());
            } else if (roleId == ContantUtil.ROLE_ADMIN) {
                Karaoke karaoke = new Karaoke();
                karaoke.setId(0L);
                karaoke.setName("Chọn Quán Karaoke");
                karaokeList.add(karaoke);
                karaokeList.addAll(karaokeService.findAll());
            }
            model.addAttribute("commentDTO", commentDTO);
            model.addAttribute("karaokeList", karaokeList);

            return "backend/comment_form";
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

            Comment comment = commentService.findById(id);
            if (comment == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("commentDTO", commentMapper.toDTO(comment));
            model.addAttribute("karaokeList", karaokeList);
            model.addAttribute("errorList", new HashMap<>());
            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/comment_form";
        } catch (Exception ex) {
            ex.getMessage();
            return "redirect:" + redirectUrl;
        }

    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, CommentDTO commentDTO, BindingResult bindingResult, Authentication authentication) {
        String redirectUrl = "/quan-ly/binh-luan/";

        try {
            commentValidator.validate(commentDTO, bindingResult);

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
                return "/backend/comment_form";
            } else {

                //save
                commentMapper.toEntity(commentDTO);

                redirectUrl = "/quan-ly/binh-luan/bieu-mau/" + commentDTO.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }
}
