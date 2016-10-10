package hu.tigra.jee.data;

/**
 * Created by Ádám on 2016.10.10..
 */

import hu.tigra.jee.model.Allocation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class AllocationRepository {

    @Inject
    private EntityManager em;

    List<Allocation> findAllOrderedBystart() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Allocation> criteria = cb.createQuery(Allocation.class);
        Root<Allocation> allocation = criteria.from(Allocation.class);

        criteria.select(allocation).orderBy(cb.asc(allocation.get("Start")));

        return em.createQuery(criteria).getResultList();
    }
}
