package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.Status;

import java.util.List;

public interface StatusDao {
    List<Status> findAll();

    Status findById(long id);

    Status findByName(String statusName);
}
