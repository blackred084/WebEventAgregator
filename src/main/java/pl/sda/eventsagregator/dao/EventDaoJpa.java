package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.Event;
import pl.sda.eventsagregator.entities.HashTag;

import java.time.LocalDate;
import java.util.List;

@Component
public class EventDaoJpa extends AbstractDaoJpa<Event> implements EventDao {

    public EventDaoJpa () {
        setClass(Event.class);
    }

    @Override
    public List<Event> findByDates(LocalDate start, LocalDate end) {
        return entityManager
                .createQuery("SELECT e FROM Event e"
                                + " WHERE e.startDate >=: startDate_param AND e.endDate <=: endDate_param"
                        , Event.class)
                .setParameter("startDate_param", start)
                .setParameter("endDate_param", end)
                .getResultList();
    }

    @Override
    public Event findByTitle(String title) {
        return entityManager
                .createQuery("SELECT e FROM Event e"
                                + " WHERE e.title =: title_param"
                        , Event.class)
                .setParameter("title_param", title)
                .getSingleResult();
    }

    @Override
    public List<HashTag> getHashTagsByEventId(long id) {
        return entityManager
                .createQuery("SELECT ht FROM HashTag ht " +
                                "JOIN EventHashTag  eht ON ht.id = eht.hashTagId " +
                                "JOIN  Event e ON eht.eventId = e.id " +
                                "WHERE e.id =: id_param"
                        , HashTag.class)
                .setParameter("id_param", id)
                .getResultList();
    }


}
