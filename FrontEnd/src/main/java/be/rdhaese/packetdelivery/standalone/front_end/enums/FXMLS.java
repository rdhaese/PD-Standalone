package be.rdhaese.packetdelivery.standalone.front_end.enums;

import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractController;

/**
 * Created on 13/01/2016.
 *
 * @author Robin D'Haese
 */
public enum FXMLS{
    ABOUT("about"),
    ADD_PACKET("add-packet"),
            EDIT_CONTACT_INFORMATION("edit-contact-information"),
    FROM_URL("from-url"),
    LOGIN_FORM("login-form"),
    LOST_PACKETS("lost-packets"),
    MENU_BAR("menu-bar"),
    OVERVIEW("overview"),
    PROBLEMATIC_DELIVERIES("problematic-deliveries"),
    PROBLEMATIC_DELIVERY("problematic-delivery"),
    EDIT_PROBLEMATIC_DELIVERY_ADDRESS("edit-problematic-delivery-address")
    ;

    public static final String LOCATION = "/fxml/";
    public static final String EXTENSION = ".fxml";

    private String toStringValue;
    private FXMLS(String toStringValue){
        this.toStringValue = toStringValue;
    }

    @Override
    public String toString() {
        return toStringValue;
    }
}
