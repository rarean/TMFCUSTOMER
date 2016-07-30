package org.tmforum.projects.api.event;

import org.tmforum.projects.core.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerEventFacade extends AbstractFacade<CustomerEvent>{
    
    @PersistenceContext(unitName = "DSCustomerPU")
    private EntityManager em;
   

    
    public CustomerEventFacade() {
        super(CustomerEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
