package hu.tigra.jee.data;

import hu.tigra.jee.model.Allocation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Ádám on 2016.10.10..
 */
@RequestScoped
public class AllocationListProducer {

    @Inject
    private AllocationRepository allocationRepository;

    private List<Allocation> allocations;

    @Produces
    @Named
    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void onAllocationListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Allocation allocation) {
        retrieveAllAllocationsOrderedByStart();
    }

    @PostConstruct
    public void retrieveAllAllocationsOrderedByStart() {
        allocations = allocationRepository.findAllOrderedBystart();
    }

}
