package org.tmforum.projects.api.event;

import org.tmforum.projects.core.exceptions.BadUsageException;
import org.tmforum.projects.api.model.CustomerAccount;
import org.tmforum.projects.api.model.Hub;
import org.tmforum.projects.core.facade.HubFacade;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class CustomerAccountEventPublisher implements CustomerAccountEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    CustomerAccountEventFacade eventFacade;
    @EJB
    CustomerAccountRESTEventPublisherLocal restEventPublisherLocal;

    /*
     * Add business logic below. (Right-click in editor and choose
     * "Insert Code > Add Business Method")
     * Access Hubs using callbacks and send to http publisher 
     *(pool should be configured around the RESTEventPublisher bean)
     * Loop into array of Hubs
     * Call RestEventPublisher - Need to implement resend policy plus eviction
     * Filtering is done in RestEventPublisher based on query expression
    */ 
    @Override
    public void publish(CustomerAccountEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(CustomerAccountEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(CustomerAccount bean, Date date) {
        CustomerAccountEvent event = new CustomerAccountEvent();
        event.setEventTime(date);
        event.setEventType(CustomerAccountEventTypeEnum.CustomerAccountCreateNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deleteNotification(CustomerAccount bean, Date date) {
        CustomerAccountEvent event = new CustomerAccountEvent();
        event.setEventTime(date);
        event.setEventType(CustomerAccountEventTypeEnum.CustomerAccountDeleteNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(CustomerAccount bean, Date date) {
        CustomerAccountEvent event = new CustomerAccountEvent();
        event.setEventTime(date);
        event.setEventType(CustomerAccountEventTypeEnum.CustomerAccountUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

}
