package pl.sda.eventsagregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.eventsagregator.dao.UserDao;
import pl.sda.eventsagregator.entities.AppUser;
import pl.sda.eventsagregator.entities.AppUserDto;
import pl.sda.eventsagregator.listenerFiles.ActiveUserStore;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;

    @Autowired
    private ActiveUserStore activeUserStore;

    @RequestMapping(value = "/loggedUsers", method = RequestMethod.GET)
    public String getLoggedUsers(Locale locale, Model model) {
        model.addAttribute("users", activeUserStore.getUsers());
        return "users";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserDao userDao) {
            this.userDao = userDao;
        }

        @GetMapping
        @ResponseBody
        List<AppUserDto> findAll () {
            List<AppUserDto> result = new LinkedList<>();
            userDao.findAll().forEach(event -> result.add(new AppUserDto(event)));
            return result;
        }


        @GetMapping("/id/{id:\\d+}")
        @ResponseBody
        AppUserDto findById ( @PathVariable("id") long id){
            AppUser appUser = userDao.findById(id);
            if (appUser == null) {
                return null;
            }
            return new AppUserDto(appUser);
        }

        @GetMapping("/username/{username}")
        @ResponseBody
        AppUserDto findById (@PathVariable("username") String username){
            AppUser appUser = userDao.findByUserName(username);
            if (appUser == null) {
                return null;
            }
            return new AppUserDto(appUser);
        }
    }

