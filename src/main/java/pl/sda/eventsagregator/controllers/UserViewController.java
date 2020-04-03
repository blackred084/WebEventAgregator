package pl.sda.eventsagregator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userView")
public class UserViewController {

    @GetMapping
    String mainPage() {
        return "userView";
    }
}
