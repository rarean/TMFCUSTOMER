/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.resources;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.dropwizard.jersey.PATCH;
import org.tmforum.projects.api.event.CustomerAccountEvent;
import org.tmforum.projects.api.event.CustomerAccountEventFacade;
import org.tmforum.projects.api.event.CustomerAccountEventPublisherLocal;
import org.tmforum.projects.api.model.CustomerAccount;
import org.tmforum.projects.core.exceptions.BadUsageException;
import org.tmforum.projects.core.exceptions.UnknownResourceException;
import org.tmforum.projects.core.facade.CustomerAccountFacade;
import org.tmforum.projects.core.utils.Jackson;
import org.tmforum.projects.core.utils.URIParser;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import java.util.*;

//@Stateless
@Path("/customerManagement/v2/customerAccount")
public class CustomerAccountResource {

    @EJB
    CustomerAccountFacade customerFacade;
    @EJB
    CustomerAccountEventFacade eventFacade;
    @EJB
    CustomerAccountEventPublisherLocal publisher;

    public CustomerAccountResource() {
    }

    /**
     * Test purpose only
     */
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(CustomerAccount entity, @Context UriInfo info) throws BadUsageException, UnknownResourceException {
        customerFacade.checkCreationOrUpdate(entity);
        customerFacade.create(entity);
        entity.setHref(info.getAbsolutePath() + "/" + Long.toString(entity.getId()));
        customerFacade.edit(entity);
        publisher.createNotification(entity, new Date());
        // 201
        Response response = Response.status(Response.Status.CREATED).entity(entity).build();
        return response;
    }

    @GET
    @Produces({"application/json"})
    public Response find(@Context UriInfo info) throws BadUsageException {

        // search queryParameters
        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();

        Map<String, List<String>> mutableMap = new HashMap();
        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        // fields to filter view
        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        Set<CustomerAccount> resultList = findByCriteria(mutableMap);

        Response response;
        if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
            response = Response.ok(resultList).build();
        } else {
            fieldSet.add(URIParser.ID_FIELD);
            List<ObjectNode> nodeList = Jackson.createNodes(resultList, fieldSet);
            response = Response.ok(nodeList).build();
        }
        return response;
    }

    // return Set of unique elements to avoid List with same elements in case of join
    private Set<CustomerAccount> findByCriteria(Map<String, List<String>> criteria) throws BadUsageException {

        List<CustomerAccount> resultList = null;
        if (criteria != null && !criteria.isEmpty()) {
            resultList = customerFacade.findByCriteria(criteria, CustomerAccount.class);
        } else {
            resultList = customerFacade.findAll();
        }
        if (resultList == null) {
            return new LinkedHashSet<CustomerAccount>();
        } else {
            return new LinkedHashSet<CustomerAccount>(resultList);
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response get(@PathParam("id") long id, @Context UriInfo info) throws UnknownResourceException {

        // search queryParameters
        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();

        Map<String, List<String>> mutableMap = new HashMap();
        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        // fields to filter view
        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        CustomerAccount customer = customerFacade.find(id);
        Response response;

        // If the result list (list of bills) is not empty, it conains only 1 unique bill
        if (customer != null) {
            // 200
            if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
                response = Response.ok(customer).build();
            } else {
                fieldSet.add(URIParser.ID_FIELD);
                ObjectNode node = Jackson.createNode(customer, fieldSet);
                response = Response.ok(node).build();
            }
        } else {
            // 404 not found
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/json"})
//    @Produces({"application/json"})
//    public Response update(@PathParam("id") long id, CustomerAccount entity) throws UnknownResourceException {
//        Response response = null;
//        CustomerAccount customer = customerFacade.find(id);
//        if (customer != null) {
//            entity.setId(id);
//            customerFacade.edit(entity);
//            publisher.valueChangedNotification(entity, new Date());
//            // 201 OK + location
//            response = Response.status(Response.Status.CREATED).entity(entity).build();
//
//        } else {
//            // 404 not found
//            response = Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return response;
//    }
    /*
     *
     * For test purpose only
     *
     * @param id
     * @return
     * @throws UnknownResourceException
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) throws UnknownResourceException {
        CustomerAccount entity = customerFacade.find(id);

        // Event deletion
        publisher.deleteNotification(entity, new Date());
        try {
            //Pause for 4 seconds to finish notification
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            // Log someting to the console (should never happen)
        }
        // remove event(s) binding to the resource
        List<CustomerAccountEvent> events = eventFacade.findAll();
        for (CustomerAccountEvent event : events) {
            if (event.getResource().getId().equals(id)) {
                eventFacade.remove(event.getId());
            }
        }
        //remove resource
        customerFacade.remove(id);
        // 204 
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response patch(@PathParam("id") long id, CustomerAccount partialCustomerAccount) throws BadUsageException, UnknownResourceException {
        Response response = null;

        customerFacade.checkCreationOrUpdate(partialCustomerAccount);

        CustomerAccount currentCustomerAccount = customerFacade.patchAttributs(id, partialCustomerAccount);

        // 200 OK + location
        response = Response.status(Response.Status.OK).entity(currentCustomerAccount).build();

        return response;
    }

}
