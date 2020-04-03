package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRole implements Serializable {

    public UserRole(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long roleId;
}
