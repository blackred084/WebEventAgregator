package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.Participation;

@Component
public class ParticipationDaoJpa extends AbstractDaoJpa<Participation> implements ParticipationDao {

    public ParticipationDaoJpa () {
        setClass(Participation.class);
    }
}
