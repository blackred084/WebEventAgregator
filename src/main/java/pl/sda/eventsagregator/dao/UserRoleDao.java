package pl.sda.eventsagregator.dao;

import pl.sda.eventsagregator.entities.UserRole;

import java.util.List;

public interface UserRoleDao {
    List<UserRole> findAll ();

    UserRole findById ( long id );

    void create ( UserRole userRole );

    UserRole update ( UserRole userRole );

    void delete ( UserRole userRole );

    void deleteById ( long id );
}
