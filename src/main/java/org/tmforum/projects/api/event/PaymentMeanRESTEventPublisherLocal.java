package org.tmforum.projects.api.event;

import org.tmforum.projects.api.model.Hub;

import javax.ejb.Local;

@Local
public interface PaymentMeanRESTEventPublisherLocal {

    public void publish(Hub hub, PaymentMeanEvent event);
    
}
