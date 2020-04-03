package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserStatus implements Serializable {

    public UserStatus(long userId, long statusId) {
        this.userId = userId;
        this.statusId = statusId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

    private long statusId;
}
