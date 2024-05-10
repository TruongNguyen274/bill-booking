package vn.billbooking.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.dto.MessageDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.service.LocationService;
import vn.billbooking.utils.ValidatorUtil;
import vn.billbooking.validator.LocationValidator;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/quan-ly/khu-vuc")
public class LocationManagerController {
    private final String redirectUrl = "/quan-ly/khu-vuc/";
    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationValidator locationValidator;

    @Autowired
    private ValidatorUtil validatorUtil;

    @GetMapping(value = {"", "/"})
    public String list(Model model) {
        try {
            List<Location> locations = locationService.findAll();
            model.addAttribute("locationList", locationMapper.toListDTO(locations));

            return "backend/location_list";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau"})
    public String create(Model model) {
        try {
            model.addAttribute("locationDTO", new LocationDTO());
            return "backend/location_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @GetMapping(value = {"/bieu-mau/{id}"})
    public String edit(Model model, @PathVariable long id, @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Location location = locationService.findById(id);
            if (location == null) {
                return "redirect:" + redirectUrl;
            }

            model.addAttribute("locationDTO", locationMapper.toDTO(location));
            model.addAttribute("errorList", new HashMap<>());
            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success") ? "Cập nhật dữ liệu thành công!" : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "backend/location_form";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

    @PostMapping(value = "/bieu-mau/")
    public String save(Model model, LocationDTO locationDTO, BindingResult bindingResult) {
        String redirectUrl = "/quan-ly/khu-vuc/";
        try {
            locationValidator.validate(locationDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", validatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "/backend/location_form";
            } else {
                Location location = locationMapper.toEntity(locationDTO);

                locationService.save(location);

                redirectUrl = "/quan-ly/khu-vuc/bieu-mau/" + location.getId() + "?action=save&status=success";
                return "redirect:" + redirectUrl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:" + redirectUrl;
        }
    }

}
