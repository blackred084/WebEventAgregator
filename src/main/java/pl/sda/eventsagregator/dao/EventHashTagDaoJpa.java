package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.EventHashTag;

@Component
public class EventHashTagDaoJpa extends AbstractDaoJpa<EventHashTag> implements EventHashTagDao {

    public EventHashTagDaoJpa() {
        setClass(EventHashTag.class);
    }
}
