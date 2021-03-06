/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.api.event;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.tmforum.projects.api.model.Customer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.ANY;

@XmlRootElement
@Entity
@Table(name="Event_Customer")
@JsonPropertyOrder(value = {"id", "eventTime", "eventType", "event"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CustomerEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   @JsonProperty("eventId")
    private String id;
    

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize//(using = CustomJsonDateSerializer.class)
    private Date eventTime;

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Enumerated(value = EnumType.STRING)
    private CustomerEventTypeEnum eventType;

    private Customer resource; //check for object

   @JsonProperty("eventId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public Customer getResource() {
        
        
        return resource;
    }

    public void setResource(Customer resource) {
        this.resource = resource;
    }


    public CustomerEventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(CustomerEventTypeEnum eventType) {
        this.eventType = eventType;
    }

     @JsonAutoDetect(fieldVisibility = ANY)
    class EventBody {
        private Customer customer;
        public Customer getTroubleTicket() {
            return customer;
        }
        public EventBody(Customer customer) { 
        this.customer = customer;
    }
    
       
    }
   @JsonProperty("event")
   public EventBody getEvent() {
       
       return new EventBody(getResource() );
   }

    @Override
    public String toString() {
        return "CustomerEvent{" + "id=" + id + ", eventTime=" + eventTime + ", eventType=" + eventType + ", resource=" + resource + '}';
    }
    

    

}
