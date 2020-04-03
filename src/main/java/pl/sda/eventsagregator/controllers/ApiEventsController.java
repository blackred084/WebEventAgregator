package pl.sda.eventsagregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.eventsagregator.dao.EventDao;
import pl.sda.eventsagregator.entities.Event;
import pl.sda.eventsagregator.entities.EventDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/api/events")
public class ApiEventsController {

    private final EventDao eventDao;

    private final LocalDateTime startDate = LocalDateTime.of(1990, 1, 1, 0, 0);
    private final LocalDateTime endDate = LocalDateTime.of(9999, 1, 1, 0, 0);

    @Autowired
    public ApiEventsController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GetMapping
    @ResponseBody
    List<EventDto> findAll(
            @RequestParam(value = "sYear", defaultValue = "0") int sYear,
            @RequestParam(value = "sMonth", defaultValue = "1") int sMonth,
            @RequestParam(value = "sDay", defaultValue = "1") int sDay,
            @RequestParam(value = "sHour", defaultValue = "0") int sHour,
            @RequestParam(value = "eYear", defaultValue = "9999") int eYear,
            @RequestParam(value = "eMonth", defaultValue = "1") int eMonth,
            @RequestParam(value = "eDay", defaultValue = "1") int eDay,
            @RequestParam(value = "eHour", defaultValue = "0") int eHour){
        List<EventDto> result = new LinkedList<>();
        LocalDate start = LocalDate.of(sYear, sMonth, sDay);
        LocalDate end = LocalDate.of(eYear, eMonth, eDay);
        eventDao.findByDates(start, end).forEach(event -> result.add(new EventDto(event)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println(currentUserName);


        return result;
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    EventDto findOne(@PathVariable("id") long id) {
        Event event = eventDao.findById(id);
        if (event == null) {
            return null;
        }
        return new EventDto(event);
    }
}
