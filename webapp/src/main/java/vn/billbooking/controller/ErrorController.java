package vn.billbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    // for 403 access denied page
    @GetMapping(value = "/error/403")
    public String accesssDenied() {
        return "backend/error_403";
    }

}
