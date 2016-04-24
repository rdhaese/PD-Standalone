package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.RegionsLogger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class RegionsLoggerImpl extends AbstractLogger implements RegionsLogger {

    @Autowired
    @Qualifier("regionsLogger")
    private Logger logger;

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService.regions(..))")
    public void afterRegions() {
        String logText = String.format(
                "Regions requested by [%s]",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
