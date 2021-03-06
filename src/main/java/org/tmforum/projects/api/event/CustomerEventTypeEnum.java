/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.api.event;

public enum CustomerEventTypeEnum {

    CustomerCreateNotification("CustomerCreateNotification"),
    CustomerUpdateNotification("CustomerUpdateNotification"),
    CustomerDeleteNotification("CustomerDeleteNotification");

    private String text;

    CustomerEventTypeEnum(String text) {
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
    public static org.tmforum.projects.api.event.CustomerEventTypeEnum fromString(String text) {
        if (text != null) {
            for (CustomerEventTypeEnum b : CustomerEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}