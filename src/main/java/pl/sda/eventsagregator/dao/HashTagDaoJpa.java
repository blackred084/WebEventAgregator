package pl.sda.eventsagregator.dao;

import org.springframework.stereotype.Component;
import pl.sda.eventsagregator.entities.HashTag;

@Component
public class HashTagDaoJpa extends AbstractDaoJpa<HashTag> implements HashTagDao {

    public HashTagDaoJpa() {
        setClass(HashTag.class);
    }

    @Override
    public HashTag findByTag(String tag) {
        return entityManager
                .createQuery("SELECT ht FROM HashTag ht WHERE ht.tag =: tag_param"
                        , HashTag.class)
                .setParameter("tag_param", tag)
                .getSingleResult();
    }

    @Override
    public boolean isPresentByTag(String tag) {
        return entityManager
                .createQuery("SELECT count(ht) FROM HashTag ht WHERE ht.tag =: tag_param"
                        , Long.class)
                .setParameter("tag_param", tag)
                .getSingleResult() == 1L;
    }
}
