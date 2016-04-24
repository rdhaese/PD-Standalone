package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.OptionsLogger;
import org.aspectj.lang.JoinPoint;
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
public class OptionsLoggerImpl extends AbstractLogger implements OptionsLogger {

    @Autowired
    @Qualifier("optionsLogger")
    private Logger logger;

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService.getFor(..))")
    public void afterGetFor(JoinPoint joinPoint) {
        String logText = String.format(
                "Options requested by [%s]",
                getLoggedInUser()
                );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService.save(..))")
    public void afterSave(JoinPoint joinPoint) {
        String logText = String.format(
                "Options saved by [%s]",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
