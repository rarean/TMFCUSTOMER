package org.tmforum.projects.api.event;

import org.tmforum.projects.core.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerAccountEventFacade extends AbstractFacade<CustomerAccountEvent>{
    
    @PersistenceContext(unitName = "DSCustomerPU")
    private EntityManager em;
   

    
    /**
     *
     */
    public CustomerAccountEventFacade() {
        super(CustomerAccountEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
