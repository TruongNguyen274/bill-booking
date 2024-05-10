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
import java.util.Arrays;
import java.util.List;

@Controller
public class ShopPageController {

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

    @GetMapping(value = {"/cua-hang.html"})
    public String index(Model model, Authentication authentication, @RequestParam(required = false) String locationId,
                        @RequestParam(required = false) String pointIds, @RequestParam(required = false) String requestId) {
        List<Location> locationList =  locationService.findByActive();
        Location location = locationList.get(0);

        if (locationId == null || locationId.equalsIgnoreCase("")) {
            String redirectUrl = "/cua-hang.html?locationId=" + location.getId();
            return "redirect:" + redirectUrl;
        }

        if (locationId != null && !locationId.isEmpty()) {
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

        if (requestId == null) {
            karaokeList = karaokeService.findByLocation(location.getId());
        } else {
            String[] str = pointIds != null && !pointIds.equalsIgnoreCase("") && !pointIds.equalsIgnoreCase("null")
                    ? pointIds.split(",") : new String[]{};

            List<String> list = new ArrayList<String>(Arrays.asList(str));

            for (PointDTO p : pointList) {
                p.setStatus(false);

                boolean isContain = Arrays.stream(str).anyMatch(String.valueOf(p.getId())::equals);
                if (isContain) {
                    p.setStatus(true);
                }

                if (requestId != null && !requestId.equalsIgnoreCase("") && !requestId.equalsIgnoreCase("null")) {
                    boolean contains = Arrays.stream(str).anyMatch(requestId::equals);

                    if (String.valueOf(p.getId()).equalsIgnoreCase(requestId) && !contains) {
                        p.setStatus(true);
                        list.add(String.valueOf(p.getId()));
                    }

                    if (String.valueOf(p.getId()).equalsIgnoreCase(requestId) && contains) {
                        p.setStatus(false);
                        list.remove(requestId);
                    }
                }
            }

            String[] newStr = list.toArray(String[]::new);
            pointIds = String.join(",", newStr);
            karaokeList = karaokeService.findByPoint(newStr);
        }

        model.addAttribute("karaokeList", karaokeMapper.toListDTO(karaokeList));
        model.addAttribute("pointList", pointList);
        model.addAttribute("listPointId", pointIds);
        model.addAttribute("locationId", location.getId());

        return "frontend/shop";
    }

}
