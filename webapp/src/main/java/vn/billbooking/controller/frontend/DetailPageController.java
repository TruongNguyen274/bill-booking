package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.KaraokeImage;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.KaraokeImageMapper;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.service.CustomUserDetails;
import vn.billbooking.service.KaraokeImageService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.LocationService;

import java.util.List;

@Controller
public class DetailPageController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private KaraokeImageService karaokeImageService;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private KaraokeImageMapper karaokeImageMapper;

    @Autowired
    private LocationMapper locationMapper;

    @GetMapping(value = {"/chi-tiet.html"})
    public String homePage(Model model, Authentication authentication, @RequestParam(required = false) String location,
            @RequestParam(required = false) String ownerId) {
        if (ownerId == null) {
            String redirectUrl = "/dang-nhap";
            return "redirect:" + redirectUrl;
        }

        Karaoke karaoke = karaokeService.findById(Long.parseLong(ownerId));
        if (karaoke == null) {
            String redirectUrl = "/dang-nhap";
            return "redirect:" + redirectUrl;
        }

        long locationId = 0;

        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = customUserDetails.getUser();
            Location loc = account.getLocation();
            if (loc != null) {
                locationId = loc.getId();
            }
        }

        List<Karaoke> karaokeRandomList = karaokeService.findByRandom(8);
        List<Karaoke> karaokeLocationList = karaokeService.findByLocation(2, locationId);
        List<Location> locationList = locationService.findByActive();
        List<KaraokeImage> karaokeImageList = karaokeImageService.findByKaraoke(karaoke);

        model.addAttribute("ownerId", ownerId);
        model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));
        model.addAttribute("karaokeRandomList", karaokeMapper.toListDTO(karaokeRandomList));
        model.addAttribute("karaokeLocationList", karaokeMapper.toListDTO(karaokeLocationList));
        model.addAttribute("karaokeImageList", karaokeImageMapper.toListDTO(karaokeImageList));
        model.addAttribute("locationList", locationMapper.toListDTO(locationList));

        return "frontend/detail";
    }

}
