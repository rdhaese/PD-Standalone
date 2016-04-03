package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.AuthenticationLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
public class AuthenticationLoggerImpl implements AuthenticationLogger {

    @Autowired
    @Qualifier("authenticationLoggerBean")
    private Logger logger;

    //TODO LOGGING NOT WORKING
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticationWebService.authenticate(..))",
            returning = "loggedIn")
    public void afterAuthenticationAttempt(JoinPoint joinPoint, boolean loggedIn){
        String username = joinPoint.getArgs()[0].toString();
        if(loggedIn){
            logger.info(String.format("Logged in as: %s", username));
        } else {
            logger.warn(String.format("Failed login attempt with username: %s", username));
        }
    }
}
