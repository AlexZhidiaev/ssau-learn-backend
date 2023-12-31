package ssau.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(path = { "", "/home", "/login" })
    public String home() {
        return "forward:/index.html";
    }
}