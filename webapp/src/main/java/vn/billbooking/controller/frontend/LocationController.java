package vn.billbooking.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocationController {

    @GetMapping(value = {"/vi-tri.html"})
    public String homePage(@RequestParam(required = false) String locationId) {
        if (locationId == null || locationId.equalsIgnoreCase("")) {
            String redirectUrl = "/trang-chu.html";
            return "redirect:" + redirectUrl;
        }

        String redirectUrl = "/trang-chu.html?locationId=" + locationId;
        return "redirect:" + redirectUrl;
    }

}
