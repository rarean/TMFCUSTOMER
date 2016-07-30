/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.core.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("application/json")
public class JacksonConfigurator implements ContextResolver<ObjectMapper> {

    private ObjectMapper mapper = new ObjectMapper();
    
    public JacksonConfigurator() {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> arg0) {
        return mapper;
    }

}