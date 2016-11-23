package hu.tigra.jee.controller;

import hu.tigra.jee.model.Allocation;
import hu.tigra.jee.service.AllocationDelete;
import hu.tigra.jee.service.AllocationRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by Ádám on 2016.10.05..
 */
@Model
public class AllocationController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private AllocationRegistration allocationRegistration;

    @Inject
    private AllocationDelete allocationDelete;

    @Produces
    @Named
    private Allocation newAllocation;

    @Produces
    @Named
    private Date date;

    @PostConstruct
    public void initnewAllocation() {
        newAllocation = new Allocation();
        date = new Date();
    }

    public void register() throws Exception {
        try {

            if (newAllocation.getStart().compareTo(date) < 0 && newAllocation.getStop().compareTo(date) < 0) {
                throw new Exception("There can be not a time traveler");
            }
            if (newAllocation.getStart().compareTo(newAllocation.getStop()) >= 0) {
                throw new Exception("There can be not a time traveler");
            }

            allocationRegistration.register(newAllocation);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initnewAllocation();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }

    public void delete() throws Exception {
        try {
            allocationDelete.delete(newAllocation.getId());
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted!", "Deleted successful!");
            facesContext.addMessage(null, m);
            initnewAllocation();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Delete unsuccessful");
            facesContext.addMessage(null, m);
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }


}

