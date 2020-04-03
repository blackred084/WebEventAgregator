package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.AppUser;
import pl.sda.eventsagregator.entities.Status;

import java.util.List;

public interface UserDao {
    List<AppUser> findAll();

    AppUser findById(long id);

    void create(AppUser appUser);

    AppUser update(AppUser appUser);

    void delete(AppUser appUser);

    void deleteById(long id);

    AppUser findByUserName(String userName);

    Status getStatus(long appUserId);
}
