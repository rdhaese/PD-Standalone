package be.rdhaese.packetdelivery.standalone.service.logging;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class EditContactInformationLogger {

    public void afterGettingContactInformation(){

    }

    public void afterSavingContactInformation(){

    }
}
