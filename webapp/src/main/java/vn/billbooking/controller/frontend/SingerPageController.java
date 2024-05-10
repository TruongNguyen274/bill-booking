package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.entity.*;
import vn.billbooking.model.mapper.*;
import vn.billbooking.service.*;

import java.util.List;

@Controller
public class SingerPageController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private LocationMapper locationMapper;

    @GetMapping(value = {"/ca-si.html"})
    public String homePage(Model model) {
        List<Singer> singerList = singerService.findAllByActive();
        List<Location> locationList = locationService.findByActive();

        model.addAttribute("singerList", singerMapper.toListDTO(singerList));
        model.addAttribute("locationList", locationMapper.toListDTO(locationList));

        return "frontend/singer";
    }

    @GetMapping(value = {"/ca-si/chi-tiet.html"})
    public String homePage(Model model, @RequestParam(required = false) long singerId) {
        Singer singer = singerService.findById(singerId);
        List<Location> locationList = locationService.findByActive();

        if (singer == null) {
            String redirectUrl = "/ca-si.html";
            return "redirect:" + redirectUrl;
        }

        model.addAttribute("singerDTO", singerMapper.toDTO(singer));
        model.addAttribute("locationList", locationMapper.toListDTO(locationList));

        return "frontend/singer_detail";
    }

}
