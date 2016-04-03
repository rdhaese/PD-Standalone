<<<<<<< HEAD:front-end/java-fx-implementation/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/java_fx_implementation/enums/FXMLS.java
package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums;
=======
package be.rdhaese.packetdelivery.standalone.front_end.enums;
>>>>>>> 7362763c2cab397bd2f065faa67fbe81a313c24b:FrontEnd/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/enums/FXMLS.java

/**
 * Created on 13/01/2016.
 *
 * @author Robin D'Haese
 */
<<<<<<< HEAD:front-end/java-fx-implementation/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/java_fx_implementation/enums/FXMLS.java
public enum FXMLS {
=======
public enum FXMLS{
>>>>>>> 7362763c2cab397bd2f065faa67fbe81a313c24b:FrontEnd/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/enums/FXMLS.java
    OPTIONS("options"),
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
