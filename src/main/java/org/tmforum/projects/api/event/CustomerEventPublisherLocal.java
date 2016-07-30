package org.tmforum.projects.api.event;

import org.tmforum.projects.api.model.Customer;

import javax.ejb.Local;
import java.util.Date;


@Local
public interface CustomerEventPublisherLocal {

    void publish(CustomerEvent event);

    /*
     *
     * CreateNotification
     * @param bean the bean which has been created
     * @param reason the related reason
     * @param date the creation date
     */
    public void createNotification(Customer bean, Date date);

    /*
     *
     * DeletionNotification
     * @param bean the bean which has been deleted
     * @param reason the reason of the deletion
     * @param date the deletion date
     */
    public void deleteNotification(Customer bean, Date date);

    /*
     *
     * UpdateNotification (PATCH)
     * @param bean the bean which has been updated
     * @param reason the reason it has been updated for
     * @param date the update date
     */
    public void updateNotification(Customer bean, Date date);

}
