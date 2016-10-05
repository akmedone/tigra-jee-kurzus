package hu.tigra.jee.service;

/**
 * Created by Ádám on 2016.10.05..
 */

import hu.tigra.jee.model.Allocation;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;


@Stateless
public class AllocationRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Allocation> allocationEvent;

    public void register(Allocation allocation) throws Exception {
        log.info("Registering" + allocation.getSubject());
        em.persist(allocation);
        allocationEvent.fire(allocation);
    }


}