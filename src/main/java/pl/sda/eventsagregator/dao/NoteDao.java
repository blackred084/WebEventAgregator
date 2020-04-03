package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.Note;

import java.util.List;

public interface NoteDao {
    List<Note> findAll();

    Note findById(long id);

    void create(Note note);

    Note update(Note note);

    void delete(Note note);

    void deleteById(long id);
}
