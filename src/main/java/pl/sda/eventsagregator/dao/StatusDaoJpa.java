package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.Status;

@Component
public class StatusDaoJpa extends AbstractDaoJpa<Status> implements StatusDao {

    public StatusDaoJpa() {
        setClass(Status.class);
    }

    @Override
    public Status findByName(String statusName) {
        return entityManager
                .createQuery("SELECT s FROM Status s WHERE s.status=:statusName_param", Status.class)
                .setParameter("statusName_param", statusName).getSingleResult();
    }
}
