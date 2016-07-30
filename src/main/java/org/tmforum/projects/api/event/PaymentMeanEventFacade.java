package org.tmforum.projects.api.event;

import org.tmforum.projects.core.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PaymentMeanEventFacade extends AbstractFacade<PaymentMeanEvent>{
    
    @PersistenceContext(unitName = "DSCustomerPU")
    private EntityManager em;
   

    
    public PaymentMeanEventFacade() {
        super(PaymentMeanEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
