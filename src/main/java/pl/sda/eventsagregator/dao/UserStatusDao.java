package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.UserStatus;

import java.util.List;

public interface UserStatusDao {
    List<UserStatus> findAll();

    UserStatus findById(long id);

    void create(UserStatus userStatus);

    UserStatus update(UserStatus userStatus);

    void delete(UserStatus userStatus);

    void deleteById(long id);
}
