package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto implements Serializable {

    public EventDto(Event event) {
        this.id = event.getId();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.creatorId = event.getCreatorId();
        this.title = event.getTitle();
    }

    long id;

    String description;

    LocalDate startDate;

    LocalDate endDate;

    long creatorId;

    String creatorName;

    String title;

    List<String> hashTags;

    List<Like> likes;
}
