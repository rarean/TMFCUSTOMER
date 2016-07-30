/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmforum.projects.api.event;

public enum CustomerAccountEventTypeEnum {

    CustomerAccountCreateNotification("CustomerAccountCreateNotification"),
    CustomerAccountUpdateNotification("CustomerAccountUpdateNotification"),
    CustomerAccountDeleteNotification("CustomerAccountDeleteNotification");

    private String text;

    CustomerAccountEventTypeEnum(String text) {
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
    public static org.tmforum.projects.api.event.CustomerAccountEventTypeEnum fromString(String text) {
        if (text != null) {
            for (CustomerAccountEventTypeEnum b : CustomerAccountEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}