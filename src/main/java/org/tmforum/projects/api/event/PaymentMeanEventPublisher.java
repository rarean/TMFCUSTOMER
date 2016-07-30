package org.tmforum.projects.api.event;

import org.tmforum.projects.core.exceptions.BadUsageException;
import org.tmforum.projects.api.model.PaymentMean;
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
public class PaymentMeanEventPublisher implements PaymentMeanEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    PaymentMeanEventFacade eventFacade;
    @EJB
    PaymentMeanRESTEventPublisherLocal restEventPublisherLocal;

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
    public void publish(PaymentMeanEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(PaymentMeanEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(PaymentMean bean, Date date) {
        PaymentMeanEvent event = new PaymentMeanEvent();
        event.setEventTime(date);
        event.setEventType(PaymentMeanEventTypeEnum.PaymentMeanCreateNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deleteNotification(PaymentMean bean, Date date) {
        PaymentMeanEvent event = new PaymentMeanEvent();
        event.setEventTime(date);
        event.setEventType(PaymentMeanEventTypeEnum.PaymentMeanDeleteNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(PaymentMean bean, Date date) {
        PaymentMeanEvent event = new PaymentMeanEvent();
        event.setEventTime(date);
        event.setEventType(PaymentMeanEventTypeEnum.PaymentMeanUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

}
