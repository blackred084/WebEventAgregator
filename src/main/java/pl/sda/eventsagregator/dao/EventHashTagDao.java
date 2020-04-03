package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.EventHashTag;

import java.util.List;

public interface EventHashTagDao {
    List<EventHashTag> findAll();

    EventHashTag findById(long id);

    void create(EventHashTag note);

    EventHashTag update(EventHashTag note);

    void delete(EventHashTag note);

    void deleteById(long id);
}
