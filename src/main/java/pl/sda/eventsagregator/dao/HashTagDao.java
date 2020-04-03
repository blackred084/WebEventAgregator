package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.HashTag;

import java.util.List;

public interface HashTagDao {
    List<HashTag> findAll();

    HashTag findById(long id);

    void create(HashTag note);

    HashTag update(HashTag note);

    void delete(HashTag note);

    void deleteById(long id);

    HashTag findByTag(String tag);

    boolean isPresentByTag(String tag);
}
