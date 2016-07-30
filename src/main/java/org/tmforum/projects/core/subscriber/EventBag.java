package org.tmforum.projects.core.subscriber;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class EventBag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Lob
    private byte[] event;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getEvent() {
        return event;
    }

    public void setEvent(byte[] event) {
        this.event = event;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }
}
