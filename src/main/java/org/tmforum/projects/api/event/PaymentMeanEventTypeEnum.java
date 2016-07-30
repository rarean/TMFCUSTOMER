/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.api.event;

public enum PaymentMeanEventTypeEnum {

    PaymentMeanCreateNotification("PaymentMeanCreateNotification"),
    PaymentMeanUpdateNotification("PaymentMeanUpdateNotification"),
    PaymentMeanDeleteNotification("PaymentMeanDeleteNotification");

    private String text;

    PaymentMeanEventTypeEnum(String text) {
        this.text = text;
    }

    /*
     *
     * @return
     */
    public String getText() {
        return this.text;
    }

    /*
     *
     * @param text
     * @return
     */
    public static org.tmforum.projects.api.event.PaymentMeanEventTypeEnum fromString(String text) {
        if (text != null) {
            for (PaymentMeanEventTypeEnum b : PaymentMeanEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}