package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service.AuthenticationWebServiceExtended;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractLogger {

    @Autowired
    private AuthenticationWebServiceExtended authenticationService;

    protected String getLoggedInUser() {
        return authenticationService.getLoggedInUser();
    }

    protected void info(String logText) {
        getLogger().info(logText);
    }

    protected void debug(String logText) {
        getLogger().debug(logText);
    }

    public abstract Logger getLogger();

    protected <T> T getArg(JoinPoint joinpoint, Integer argIndex) {
        return (T) joinpoint.getArgs()[argIndex];
    }
}
