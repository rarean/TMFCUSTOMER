package org.tmforum.projects.api.event;

import com.fasterxml.jackson.databind.node.ObjectNode; //org.codehaus.jackson.node.ObjectNode;
import org.tmforum.projects.core.jaxrs.RESTClient;
import org.tmforum.projects.core.utils.Jackson;
import org.tmforum.projects.core.utils.URIParser;
import org.tmforum.projects.api.model.Hub;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Set;

@Stateless
@Asynchronous
public class CustomerRESTEventPublisher implements CustomerRESTEventPublisherLocal {

    @EJB
    CustomerEventFacade eventFacade;

    @EJB
    RESTClient client;

    @Override
    public void publish(Hub hub, CustomerEvent event) {

        MultivaluedMap<String, String> query = URIParser.getParameters(hub.getQuery());
        query.putSingle("id", event.getId());

        // fields to filter view
        Set<String> fieldSet = URIParser.getFieldsSelection(query);

        List<CustomerEvent> resultList = null;
        resultList = eventFacade.findByCriteria(query, CustomerEvent.class);

        if (resultList != null && !resultList.isEmpty()) {
            if (!fieldSet.isEmpty() && !fieldSet.contains(URIParser.ALL_FIELDS)) {
                fieldSet.add("id");
                fieldSet.add("date");
                fieldSet.add("eventType");
                fieldSet.add("reason");
                ObjectNode rootNode = Jackson.createNode(event, fieldSet);
                client.publishEvent(hub.getCallback(), rootNode);
            } else {
                client.publishEvent(hub.getCallback(), event);
            }
            
        }
    }

}
