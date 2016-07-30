package org.tmforum.projects.api.event;

import org.tmforum.projects.api.model.Hub;

import javax.ejb.Local;

@Local
public interface CustomerAccountRESTEventPublisherLocal {

    public void publish(Hub hub, CustomerAccountEvent event);
    
}
