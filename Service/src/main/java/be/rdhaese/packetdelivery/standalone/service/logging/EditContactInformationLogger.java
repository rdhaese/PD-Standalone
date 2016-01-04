package be.rdhaese.packetdelivery.standalone.service.logging;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class EditContactInformationLogger {

    @Autowired
    @Qualifier("editContactInformationLoggerBean")
    private Logger logger;


    public void afterSavingContactInformation(){

    }
}
