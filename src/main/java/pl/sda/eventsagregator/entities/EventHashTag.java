package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventHashTag implements Serializable {

    public EventHashTag(long hashTagId, long eventId) {
        this.hashTagId = hashTagId;
        this.eventId = eventId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long hashTagId;

    @Column(nullable = false)
    private long eventId;
}
