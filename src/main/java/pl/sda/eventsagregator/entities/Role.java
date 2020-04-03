package pl.sda.eventsagregator.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Role implements Serializable {

    @Id
    private long id;

    @Column(nullable = false)
    private String roleName;

    private String description;
}
