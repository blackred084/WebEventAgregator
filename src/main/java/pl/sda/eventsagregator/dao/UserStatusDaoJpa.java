package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.UserStatus;

@Component
public class UserStatusDaoJpa extends AbstractDaoJpa<UserStatus> implements UserStatusDao{

    public UserStatusDaoJpa() {
        setClass(UserStatus.class);
    }
}
