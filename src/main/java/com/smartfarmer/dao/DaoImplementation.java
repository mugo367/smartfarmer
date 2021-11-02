package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.DaoI;
import com.smartfarmer.util.ModelListWrapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.REQUIRED;

public abstract class DaoImplementation<T,P> implements DaoI<T,P> {

    private final Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    public DaoImplementation(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Transactional(REQUIRED)
    @Override
    public T save(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
        return t;
    }

    @Transactional(REQUIRED)
    @Override
    public T edit(T t) {
        return getEntityManager().merge(t);
    }

    @Transactional(REQUIRED)
    @Override
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Transactional(REQUIRED)
    @Override
    public void deleteById(P id){
        Optional<T> optionalT = findById(id);
        optionalT.ifPresent(this::delete);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ModelListWrapper<T> list(T filter, int start, int limit) {
        ModelListWrapper<T> results = new ModelListWrapper<>();

        Query query = getEntityManager().createQuery("from " + entityClass.getName());

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<T> resultList = query.getResultList();

        results.setList(resultList);
        results.setCount(this.count());

        return results;
    }

    @Override
    public boolean existsById(P id) {
        Optional<T> optionalT = findById(id);
        return optionalT.isPresent();
    }


    @Override
    public Optional<T> findById(P id) {
        T t = getEntityManager().find(entityClass, id);
        if (t != null) {
            return Optional.of(t);
        }
        return Optional.empty();
    }

    @Override
    public int count() {
        CriteriaQuery<Long> criteriaQuery =
                getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        Query query = getEntityManager().createQuery(criteriaQuery);
        return ((Long) query.getSingleResult()).intValue();
    }
}
