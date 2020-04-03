package pl.sda.eventsagregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.eventsagregator.dao.EventDao;
import pl.sda.eventsagregator.dao.EventHashTagDao;
import pl.sda.eventsagregator.dao.HashTagDao;
import pl.sda.eventsagregator.dao.UserDao;
import pl.sda.eventsagregator.entities.AppUser;
import pl.sda.eventsagregator.entities.Event;
import pl.sda.eventsagregator.entities.EventHashTag;
import pl.sda.eventsagregator.entities.HashTag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/newEvent")
public class AddEventController {

    UserDao userDao;

    EventDao eventDao;

    HashTagDao hashTagDao;

    EventHashTagDao eventHashTagDao;

    @Autowired
    public AddEventController(UserDao userDao, EventDao eventDao, HashTagDao hashTagDao, EventHashTagDao eventHashTagDao) {
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.hashTagDao = hashTagDao;
        this.eventHashTagDao = eventHashTagDao;
    }

    @GetMapping
    String add() {
        return "eventArea";
    }


    @PostMapping
    String createEvent(@RequestParam(value = "tittle") String title,
                       @RequestParam(value = "comment") String description,
                       @RequestParam(value = "start") String startDateRaw,
                       @RequestParam(value = "end") String endDateRaw,
                       @RequestParam(value = "tag") String tagsRaw) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(startDateRaw, formatter);
        LocalDate endDate = LocalDate.parse(endDateRaw, formatter);

        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        event.setStartDate(startDate);
        event.setEndDate(endDate);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser loggedInUser = userDao.findByUserName(auth.getName());
        event.setCreatorId(loggedInUser.getId());

        eventDao.create(event);
        long eventId = eventDao.findByTitle(title).getId();

        String[] tagsTemp = tagsRaw.split("#");
        List<String> tags = new ArrayList<>();

        for (String tag:tagsTemp) {
            tag = tag.trim();
            if (!"".equals(tag)) {
                tags.add(tag);
            }
        }

        for (String tag : tags) {
            long hashTagId = -1;
            if(hashTagDao.isPresentByTag(tag)) {
                HashTag hashTag = hashTagDao.findByTag(tag);
                hashTagId = hashTag.getId();
            } else {
                HashTag newHashTag = new HashTag(tag);
                hashTagDao.create(newHashTag);
                hashTagId = hashTagDao.findByTag(tag).getId();
            }

            EventHashTag eventHashTag = new EventHashTag(hashTagId, eventId);
            eventHashTagDao.create(eventHashTag);
        }

        return "eventArea";
    }

}
