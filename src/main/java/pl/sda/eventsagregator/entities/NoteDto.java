package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto implements Serializable {

    public NoteDto(Note note) {
        this.id = note.getId();
        this.comment = note.getComment();
        this.idOfAppUser = note.getIdOfAppUser();
    }

    long id;

    String comment;

    AppUser idOfAppUser;

}
