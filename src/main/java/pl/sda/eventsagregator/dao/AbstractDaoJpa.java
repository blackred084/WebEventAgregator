package pl.sda.eventsagregator.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoJpa <T extends Serializable>{

    private Class<T> extendingClass;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClass(Class<T> extendingClass) {
        this.extendingClass = extendingClass;
    }

    public T findById(long id) {
        return entityManager.find(extendingClass, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("FROM " + extendingClass.getName()).getResultList();
    }

    @Transactional
    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(long id) {
        T entity = findById(id);
        delete(entity);
    }

}
