package org.tmforum.projects.core.subscriber;

import org.tmforum.projects.core.facade.AbstractFacade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ecus6396
 */
@Stateless
public class EventApiFacade extends AbstractFacade<EventBag> {

    @PersistenceContext(unitName = "DSCustomerPU")
    private EntityManager em;

    public EventApiFacade() {
        super(EventBag.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
