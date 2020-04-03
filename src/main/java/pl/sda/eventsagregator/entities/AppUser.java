package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false,unique = true)
    private  String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = false)
    private boolean publicUser;
}
