package pl.sda.eventsagregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.eventsagregator.entities.AppUserDto;
import pl.sda.eventsagregator.services.AuthorizationService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    private AuthorizationService authorizationService;

    @Autowired
    public RegisterController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    String registration() {
        return "registration";
    }

    @PostMapping
    String createUser(@RequestParam(value = "login") String login,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "passwordConfirmation") String passwordConfirmation,
                      @RequestParam(value = "email") String email) {
        if (!password.equals(passwordConfirmation)) {
            System.out.println("TODO"); // TODO password and password confirmation not matching
        }
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setLogin(login);
        appUserDto.setUsername(username);
        appUserDto.setPasswordRaw(password);
        appUserDto.setEmail(email);
        appUserDto.setCreationDate(LocalDateTime.now());

        authorizationService.register(appUserDto);

        return "mainViewStarter";
    }

}
