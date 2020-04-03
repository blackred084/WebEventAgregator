package pl.sda.eventsagregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.eventsagregator.dao.EventDao;
import pl.sda.eventsagregator.dao.EventHashTagDao;
import pl.sda.eventsagregator.dao.HashTagDao;
import pl.sda.eventsagregator.entities.Event;
import pl.sda.eventsagregator.entities.EventDto;

@Controller
@RequestMapping("/events")
public class EventsController {

    private final EventDao eventDao;

    @Autowired
    public EventsController(EventDao eventDao, HashTagDao hashTagDao, EventHashTagDao eventHashTagDao) {
        this.eventDao = eventDao;
    }

    @GetMapping("/{id:\\d+}")
    String findOne(@PathVariable("id") long id) {
        return "/";
    }

    @GetMapping("/new")
    String createEventForm() {
        return "newevent";
    }

    @PostMapping
    @ResponseBody
    long create(@RequestBody EventDto eventDto) {
        Event event = new Event();
        event.setDescription(eventDto.getDescription());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setCreatorId(eventDto.getCreatorId());
        event.setTitle(eventDto.getTitle());
        return event.getId();
    }

    @PutMapping
    @ResponseBody
    EventDto update(@RequestBody EventDto eventDto) {
        Event event = eventDao.findById(eventDto.getId());
        if (eventDto.getDescription() != null)
            event.setDescription(eventDto.getDescription());
        if (eventDto.getStartDate() != null)
            event.setStartDate(eventDto.getStartDate());
        if (eventDto.getEndDate() != null)
            event.setEndDate(eventDto.getEndDate());
        if (eventDto.getTitle() != null)
            event.setTitle(eventDto.getTitle());
        return new EventDto(event);
    }
}
