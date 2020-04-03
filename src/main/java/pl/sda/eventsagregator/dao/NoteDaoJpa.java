package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.Note;

@Component
public class NoteDaoJpa extends AbstractDaoJpa<Note> implements NoteDao {

    public NoteDaoJpa() {
        setClass(Note.class);
    }
}
