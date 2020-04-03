package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.Participation;

import java.util.List;

public interface ParticipationDao {
    List<Participation> findAll();

    Participation findById(long id);

    void create(Participation participation);

    Participation update(Participation participation);

    void delete(Participation participation);

    void deleteById(long id);
}
