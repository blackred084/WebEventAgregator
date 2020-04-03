package pl.sda.eventsagregator.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto implements Serializable {

    public ParticipationDto(Participation participation) {
        this.id = participation.getId();
        this.idOfAppUser = participation.getIdOfAppUser();
        this.idOfEvent = participation.getIdOfEvent();
    }

    long id;

    long idOfAppUser;

    long idOfEvent;

}