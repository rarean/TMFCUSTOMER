/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.core.jaxrs;

/**
 *
 * @author pierregauthier
 */

import javax.ws.rs.HttpMethod;
import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("PATCH")
@Documented
@NameBinding
public @interface PATCH {

}
