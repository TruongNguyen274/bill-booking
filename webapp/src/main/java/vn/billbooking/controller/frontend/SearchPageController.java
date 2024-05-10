package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.dto.PointDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.mapper.KaraokeMapper;
import vn.billbooking.model.mapper.PointMapper;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PointService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchPageController {

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PointService pointService;

    @Autowired
    private KaraokeMapper karaokeMapper;

    @Autowired
    private PointMapper pointMapper;

    @GetMapping(value = {"/tim-kiem.html"})
    public String index(Model model, Authentication authentication, @RequestParam(required = false) String locationId,
                        @RequestParam(required = false) String keyword) {
        List<Location> locationList =  locationService.findByActive();
        Location location = locationList.get(0);

        if (keyword == null || keyword.equalsIgnoreCase("")) {
            String redirectUrl = "/cua-hang.html?locationId=" + location.getId();
            return "redirect:" + redirectUrl;
        }

        if (locationId != null && !locationId.isEmpty() && !locationId.equalsIgnoreCase("0")) {
            location = locationService.findById(Long.parseLong(locationId));
        }

        List<PointDTO> pointList = pointMapper.toListDTO(pointService.findByLocation(location));
        if (pointList != null) {
            for (int i = 0; i < pointList.size(); i ++) {
                PointDTO p = pointList.get(i);
                p.setStatus(false);
                int count = karaokeService.countByPoint(p.getId());
                p.setCount(count);
                pointList.set(i, p);
            }
        }

        // list karaoke for pointId and location
        List<Karaoke> karaokeList = null;

        if (locationId == null || locationId.isEmpty() || locationId.equalsIgnoreCase("0")) {
            karaokeList = karaokeService.searchByKeyword(keyword);
        } else {
            karaokeList = karaokeService.searchByKeywordAndLocation(keyword, location.getId());
        }

        if (karaokeList == null) {
            karaokeList = new ArrayList<>();
        }

        model.addAttribute("karaokeList", karaokeMapper.toListDTO(karaokeList));
        model.addAttribute("pointList", pointList);
        model.addAttribute("listPointId", null);
        model.addAttribute("locationId", location.getId());

        return "frontend/shop";
    }

}
