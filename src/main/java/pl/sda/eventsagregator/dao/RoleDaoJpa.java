package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.Role;

import java.util.List;

@Component
public class RoleDaoJpa extends AbstractDaoJpa<Role> implements RoleDao {

    public RoleDaoJpa () {
        setClass(Role.class);
    }

    @Override
    public Role findByName ( String roleName ) {
        return entityManager
                .createQuery("SELECT r FROM Role r"
                                + " WHERE r.roleName = : roleName_param"
                        , Role.class)
                .setParameter("roleName_param", roleName)
                .getSingleResult();
    }

    @Override
    public List<String> getRoleNames ( long appUserId ) {
        return entityManager
                .createQuery("SELECT r.roleName FROM Role r"
                                + " LEFT JOIN UserRole ur ON r.id = ur.roleId"
                                + " LEFT JOIN AppUser au ON ur.userId = au.id"
                                + " WHERE au.id = : appUserId_param"
                        , String.class)
                .setParameter("appUserId_param", appUserId)
                .getResultList();
    }
}
