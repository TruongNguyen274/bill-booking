package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.entity.*;
import vn.billbooking.model.mapper.KaraokeImageMapper;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.model.mapper.PromotionMapper;
import vn.billbooking.service.*;

import java.util.List;

@Controller
public class VoucherPageController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private KaraokeImageService karaokeImageService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private KaraokeImageMapper karaokeImageMapper;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private LocationMapper locationMapper;

    @GetMapping(value = {"/ma-giam-gia.html"})
    public String homePage(Model model, Authentication authentication, @RequestParam(required = false) String location,
            @RequestParam(required = false) String ownerId, @RequestParam(required = false) String voucherId) {
        if (ownerId == null || voucherId == null) {
            String redirectUrl = "/dang-nhap.html";
            return "redirect:" + redirectUrl;
        }

        Karaoke karaoke = karaokeService.findById(Long.parseLong(ownerId));
        if (karaoke == null) {
            String redirectUrl = "/dang-nhap.html";
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
        Promotion promotion = promotionService.findById(Long.parseLong(voucherId));

        model.addAttribute("ownerId", ownerId);
        model.addAttribute("voucherId", voucherId);
        model.addAttribute("karaokeDTO", karaokeMapper.toDTO(karaoke));
        model.addAttribute("promotionDTO", promotionMapper.toDTO(promotion));
        model.addAttribute("karaokeRandomList", karaokeMapper.toListDTO(karaokeRandomList));
        model.addAttribute("karaokeLocationList", karaokeMapper.toListDTO(karaokeLocationList));
        model.addAttribute("karaokeImageList", karaokeImageMapper.toListDTO(karaokeImageList));
        model.addAttribute("locationList", locationMapper.toListDTO(locationList));

        return "frontend/voucher";
    }

}
