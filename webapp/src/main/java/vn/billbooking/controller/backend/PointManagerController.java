package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.dto.PointDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Point;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.model.mapper.PointMapper;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PointService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.PointValidator;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/quan-ly/khu-vuc")
public class PointManagerController {
    private final String redirectUrl = "/quan-ly/khu-vuc/";
    @Autowired
    private LocationService locationService;

    @Autowired
    private PointService pointService;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private PointValidator pointValidator;

    @Autowired
    private ValidatorUtil validatorUtil;

    @GetMapping(value = {"/{idLocation}/vi-tri"})
    public String list(Model model, @PathVariable long idLocation) {
        try {
            Location location = locationService.findById(idLocation);
            if (location == null) {
                String redirectUrl = "/quan-ly/khu-vuc/";
                return "redirect:" + redirectUrl;
            }
            List<Point> points = pointService.findByLocation(location);
            List<PointDTO> pointDTOS = pointMapper.toListDTO(points);
            model.addAttribute("pointList", pointDTOS);
            model.addAttribute("locationDTO", locationMapper.toDTO(location));

            return "backend/point_list";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/{idLocation}/vi-tri/bieu-mau"})
    public String create(Model model, @PathVariable long idLocation) {
        try {
            Location location = locationService.findById(idLocation);
            model.addAttribute("pointDTO", new PointDTO());
            model.addAttribute("locationDTO", locationMapper.toDTO(location));
            model.addAttribute("errorList", new HashMap<>());

            return "backend/point_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/{idLocation}/vi-tri/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @PathVariable long idLocation, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Point point = pointService.findById(id);
            Location location = locationService.findById(idLocation);

            if (point == null) {
                String redirectUrl = "/quan-ly/khu-vuc/" + idLocation + "/vi-tri/";
                return "redirect:" + redirectUrl;
            }
            model.addAttribute("pointDTO", pointMapper.toDTO(point));
            model.addAttribute("locationDTO", locationMapper.toDTO(location));
            model.addAttribute("errorList", new HashMap<>());

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/point_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/vi-tri/bieu-mau/")
    public String save(Model model, PointDTO pointDTO, BindingResult bindingResult) {
            String redirectUrl = "/quan-ly/khu-vuc/" + pointDTO.getLocationId() + "/vi-tri/";
        try {
            Location location = locationService.findById(pointDTO.getLocationId());
            pointValidator.validate(pointDTO, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                model.addAttribute("locationDTO", locationMapper.toDTO(location));

                return "/backend/point_form";
            } else {
                Point point = pointMapper.toEntity(pointDTO);
                point.setLocation(location);
                pointService.save(point);

                redirectUrl = "/quan-ly/khu-vuc/" + pointDTO.getLocationId() + "/vi-tri/bieu-mau/" + point.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

}
