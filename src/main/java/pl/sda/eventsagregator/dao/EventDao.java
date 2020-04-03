package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.Event;
import pl.sda.eventsagregator.entities.HashTag;

import java.time.LocalDate;
import java.util.List;


public interface EventDao {
    List<Event> findAll();

    List<Event> findByDates(LocalDate start, LocalDate end) ;

    Event findById(long id);

    Event findByTitle(String title);

    void create(Event event);

    Event update(Event event);

    void delete(Event event);

    void deleteById(long id);

    List<HashTag> getHashTagsByEventId(long id);
}
