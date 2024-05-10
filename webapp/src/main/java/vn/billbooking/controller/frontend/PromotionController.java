package vn.billbooking.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.billbooking.model.dto.LocationDTO;
import vn.billbooking.model.entity.Location;
import vn.billbooking.model.entity.Promotion;
import vn.billbooking.model.mapper.LocationMapper;
import vn.billbooking.model.mapper.PromotionMapper;
import vn.billbooking.service.LocationService;
import vn.billbooking.service.PromotionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private LocationMapper locationMapper;

    @GetMapping(value = {"/khuyen-mai.html"})
    public String index(Model model, Authentication authentication, @RequestParam(required = false) String locationId,
                        @RequestParam(required = false) String pointIds, @RequestParam(required = false) String requestId) {
        List<Location> locationList =  locationService.findByActive();
        Location location = locationList.get(0);

        if (locationId == null || locationId.equalsIgnoreCase("")) {
            String redirectUrl = "/khuyen-mai.html?locationId=" + location.getId();
            return "redirect:" + redirectUrl;
        }

        if (locationId != null && !locationId.isEmpty()) {
            location = locationService.findById(Long.parseLong(locationId));
        }

        List<LocationDTO> locationDTOList = locationMapper.toListDTO(locationList);
        if (locationDTOList != null) {
            for (int i = 0; i < locationDTOList.size(); i ++) {
                LocationDTO p = locationDTOList.get(i);
                p.setStatus(false);
                int count = promotionService.countByLocation(p.getId());
                p.setCount(count);
                locationDTOList.set(i, p);
            }
        }

        // list karaoke for pointId and location
        List<Promotion> promotionList = null;

        if (requestId == null) {
            promotionList = promotionService.findByLocation(location.getId());
        } else {
            String[] str = pointIds != null && !pointIds.equalsIgnoreCase("") && !pointIds.equalsIgnoreCase("null")
                    ? pointIds.split(",") : new String[]{};

            List<String> list = new ArrayList<String>(Arrays.asList(str));

            for (LocationDTO p : locationDTOList) {
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
            promotionList = promotionService.findByLocationIds(newStr);
        }

        if (promotionList == null) {
            promotionList = new ArrayList<>();
        }

        model.addAttribute("promotionList", promotionMapper.toListDTO(promotionList));
        model.addAttribute("pointList", locationDTOList);
        model.addAttribute("listPointId", pointIds);
        model.addAttribute("locationId", location.getId());

        return "frontend/promotion";
    }

}
