package org.tmforum.projects.api.event;

import org.tmforum.projects.api.model.PaymentMean;

import javax.ejb.Local;
import java.util.Date;


@Local
public interface PaymentMeanEventPublisherLocal {

    void publish(PaymentMeanEvent event);

    /*
     *
     * CreateNotification
     * @param bean the bean which has been created
     * @param reason the related reason
     * @param date the creation date
     */
    public void createNotification(PaymentMean bean, Date date);

    /*
     *
     * DeletionNotification
     * @param bean the bean which has been deleted
     * @param reason the reason of the deletion
     * @param date the deletion date
     */
    public void deleteNotification(PaymentMean bean, Date date);

    /*
     *
     * UpdateNotification (PATCH)
     * @param bean the bean which has been updated
     * @param reason the reason it has been updated for
     * @param date the update date
     */
    public void updateNotification(PaymentMean bean, Date date);

}
