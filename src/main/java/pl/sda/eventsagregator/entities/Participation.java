package pl.sda.eventsagregator.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Participation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    long idOfAppUser;

    @Column(nullable = false)
    long idOfEvent;

    @Column(nullable = false)
    private LocalDateTime creationDate;

}