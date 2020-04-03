package pl.sda.eventsagregator.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String editionStatus;

    @Column(nullable = false)
    private String noteType;

    @ManyToOne
    private AppUser idOfAppUser;
}
