package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto implements Serializable {

    private long id;
    private long userId;
    private long noteId;
    private LocalDateTime creationDate;
}
