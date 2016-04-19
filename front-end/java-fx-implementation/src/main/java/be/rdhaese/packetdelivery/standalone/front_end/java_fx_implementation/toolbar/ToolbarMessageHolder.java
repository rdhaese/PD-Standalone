package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.toolbar;

import org.springframework.stereotype.Component;

/**
 * Created on 18/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class ToolbarMessageHolder {

    private String message = null;

    public String getMessage() {
        String messageCopy = message;
        message = null;
        return messageCopy;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isNull(){
        return message == null;
    }
}
