package hu.tigra.jee.service;

import hu.tigra.jee.model.Allocation;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by Ádám on 2016.11.23..
 */
@Stateless
public class AllocationDelete {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Allocation> allocationEvent;

    public void delete(long id) throws Exception {
        log.info("Delete" + id);
        Allocation allocation = em.find(Allocation.class, id);
        em.remove(allocation);
        allocationEvent.fire(allocation);
    }
}
