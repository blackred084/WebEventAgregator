package pl.sda.eventsagregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.eventsagregator.dao.EventDao;
import pl.sda.eventsagregator.dao.UserDao;
import pl.sda.eventsagregator.entities.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final EventDao eventDao;

    private final UserDao userDao;

    @Autowired
    public MainPageController(EventDao eventDao, UserDao userDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    @GetMapping
    ModelAndView mainPage(HttpServletResponse response) {
        Cookie userName;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        userName = new Cookie("userName", currentPrincipalName);
        response.addCookie(userName);

        List<Event> events = eventDao.findAll();
        List<EventDto> eventDtos = new ArrayList<>();

        for (Event event : events) {
            EventDto eventDto = new EventDto(event);

            List<Like> likes = new ArrayList<>();
            eventDto.setLikes(likes);

            AppUser creator = userDao.findById(event.getCreatorId());
            eventDto.setCreatorName(creator.getUsername());

            List<HashTag> hashTags = eventDao.getHashTagsByEventId(event.getId());
            List<String> hashTagsNames = hashTags.stream().map(ht -> ht.getTag()).collect(Collectors.toList());
            eventDto.setHashTags(hashTagsNames);
            eventDtos.add(eventDto);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("events", eventDtos);

        if (currentPrincipalName != "anonymousUser") {
            modelAndView.setViewName("loggedMain");
        } else {
//            modelAndView.setViewName("mainViewStarter");
            modelAndView.setViewName("test");
        }

        return modelAndView;

    }

}
