package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.UserRole;

@Component
public class UserRoleDaoJpa extends AbstractDaoJpa<UserRole> implements UserRoleDao{

    public UserRoleDaoJpa() {
        setClass(UserRole.class);
    }
}
