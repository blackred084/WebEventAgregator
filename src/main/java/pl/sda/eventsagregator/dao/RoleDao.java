package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();

    Role findById(long id);

    Role findByName(String roleName);

    void create(Role role);

    Role update(Role role);

    void delete(Role role);

    void deleteById(long id);

    List<String> getRoleNames(long appUserId);
}
