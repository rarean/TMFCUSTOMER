/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.core.jaxrs;

import com.fasterxml.jackson.databind.JsonMappingException;// org.codehaus.jackson.map.JsonMappingException;
import org.tmforum.projects.core.exceptions.ExceptionType;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
    @Override
    public Response toResponse(JsonMappingException ex) {
        JsonFault error = new JsonFault(ExceptionType.BAD_USAGE_UNKNOWN_VALUE.getInfo(), ex.getMessage());
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(error).build();
    }
}
