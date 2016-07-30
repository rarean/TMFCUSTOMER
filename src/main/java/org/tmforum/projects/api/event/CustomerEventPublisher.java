package org.tmforum.projects.api.event;

import org.tmforum.projects.core.exceptions.BadUsageException;
import org.tmforum.projects.api.model.Customer;
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

/*
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class CustomerEventPublisher implements CustomerEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    CustomerEventFacade eventFacade;
    @EJB
    CustomerRESTEventPublisherLocal restEventPublisherLocal;

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
    public void publish(CustomerEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(CustomerEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(Customer bean, Date date) {
        CustomerEvent event = new CustomerEvent();
        event.setEventTime(date);
        event.setEventType(CustomerEventTypeEnum.CustomerCreateNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deleteNotification(Customer bean, Date date) {
        CustomerEvent event = new CustomerEvent();
        event.setEventTime(date);
        event.setEventType(CustomerEventTypeEnum.CustomerDeleteNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(Customer bean, Date date) {
        CustomerEvent event = new CustomerEvent();
        event.setEventTime(date);
        event.setEventType(CustomerEventTypeEnum.CustomerUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

}
