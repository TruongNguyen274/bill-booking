package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.entity.Gallery;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Promotion;
import vn.billbooking.model.mapper.GalleryMapper;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.PromotionMapper;
import vn.billbooking.service.GalleryService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PromotionService;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private GalleryMapper galleryMapper;

    @GetMapping(value = {"", "/", "/trang-chu.html"})
    public String homePage(Model model, Authentication authentication, @RequestParam(name = "locationId", required = false) String locationId) {
        Location location = null;

        if (locationId != null && !locationId.isEmpty()) {
            location = locationService.findById(Long.parseLong(locationId));
        } else {
            List<Location> locationList =  locationService.findByActive();
            if (locationList != null) {
                location = locationList.get(0);
            }
        }

        List<Karaoke> karaokeRandomList = null;
        List<Promotion> promotionList = null;

        if (location != null) {
            karaokeRandomList = karaokeService.findByLocation(8, location.getId());
            promotionList = promotionService.findByLocation(8, location.getId());
        }

        if (karaokeRandomList.isEmpty()) {
            karaokeRandomList = karaokeService.findByRandom(8);
        }

        if (promotionList.isEmpty()) {
            promotionList = promotionService.findByRandom(8);
        }

        // slide
        List<Gallery> gallerySlideList = galleryService.findByRandom(5, "SLIDE", true);
        List<Gallery> galleryRightList = galleryService.findByRandom(3, "BANNER_RIGHT", true);

        model.addAttribute("karaokeRandomList", karaokeMapper.toListDTO(karaokeRandomList));
        model.addAttribute("promotionList", promotionMapper.toListDTO(promotionList));
        model.addAttribute("gallerySlideList", galleryMapper.toListDTO(gallerySlideList));
        model.addAttribute("galleryRightList", galleryMapper.toListDTO(galleryRightList));

        // set banner for desktop
        Gallery galleryBannerDesktop = galleryService.findByName("HOME_BANNER_DESKTOP");
        if (galleryBannerDesktop != null && galleryBannerDesktop.isStatus() && galleryBannerDesktop.getImage() != null) {
            model.addAttribute("galleryBannerDesktop", galleryMapper.toDTO(galleryBannerDesktop));
        } else {
            model.addAttribute("galleryBannerDesktop", null);
        }

        // set banner for mobile
        Gallery galleryBannerMobile = galleryService.findByName("HOME_BANNER_MOBILE");
        if (galleryBannerMobile != null && galleryBannerMobile.isStatus() && galleryBannerMobile.getImage() != null) {
            model.addAttribute("galleryBannerMobile", galleryMapper.toDTO(galleryBannerMobile));
        } else {
            model.addAttribute("galleryBannerMobile", null);
        }

        return "frontend/home";
    }

}
