package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.AppUser;
import pl.sda.eventsagregator.entities.Status;

@Component
public class UserDaoJpa extends AbstractDaoJpa<AppUser> implements UserDao{

    public UserDaoJpa() {
        setClass(AppUser.class);
    }

    @Override
    public AppUser findByUserName(String login) {
        return entityManager
                .createQuery("SELECT u FROM AppUser u WHERE u.login =:login_param", AppUser.class)
                .setParameter("login_param", login).getSingleResult();
    }

    @Override
    public Status getStatus(long appUserId) {
        return entityManager
                .createQuery("SELECT s FROM AppUser u " +
                        "JOIN UserStatus us ON u.id = us.userId " +
                        "JOIN Status s ON s.id = us.statusId " +
                        "WHERE u.id =:appUserId_param", Status.class)
                .setParameter("appUserId_param", appUserId).getSingleResult();
    }
}
