package org.tmforum.projects.core.facade;

import org.tmforum.projects.api.model.Hub;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HubFacade extends AbstractFacade<Hub>{
    
    @PersistenceContext(unitName = "DSCustomerPU")
    private EntityManager em;

    public HubFacade() {
        super(Hub.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
