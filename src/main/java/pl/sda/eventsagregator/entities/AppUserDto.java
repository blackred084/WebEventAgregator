package pl.sda.eventsagregator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto implements Serializable {

    public AppUserDto(AppUser appUser) {
        this.id = appUser.getId();
        this.login = appUser.getLogin();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.encryptedPassword = appUser.getEncryptedPassword();
        this.publicUser = appUser.isPublicUser();
    }

    private long id;
    private String login;
    private String username;
    private LocalDateTime creationDate;
    private String email;
    private String role;
    private String passwordRaw;
    private String encryptedPassword;
    private String statusName;
    private boolean publicUser;
}
