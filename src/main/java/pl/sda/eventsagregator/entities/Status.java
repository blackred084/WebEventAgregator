package pl.sda.eventsagregator.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Status implements Serializable {

    @Id
    private long id;

    @Column(nullable = false, unique = true)
    private String status;

    private String description;
}
