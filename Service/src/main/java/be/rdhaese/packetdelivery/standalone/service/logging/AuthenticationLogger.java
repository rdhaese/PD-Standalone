package be.rdhaese.packetdelivery.standalone.service.logging;

import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class AuthenticationLogger {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationLogger.class);
    @Autowired
    private AuthenticationService authenticationService;

    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.standalone.service.AuthenticationService.authenticate(..))", returning = "loggedIn")
    public void onAuthenticationAttempt(JoinPoint joinPoint, boolean loggedIn){
        if(loggedIn){
            logger.info(String.format("Logged in as: %s", authenticationService.getLoggedInUser()));
        } else {
            logger.warn(String.format("Failed login attempt with username: %s", joinPoint.getArgs()[0]));
        }
    }
}
