package be.rdhaese.packetdelivery.standalone.service.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.standalone.service.AuthenticationService.authenticate(..))", returning = "loggedIn")
    public void afterAuthenticationAttempt(JoinPoint joinPoint, boolean loggedIn){
        String username = joinPoint.getArgs()[0].toString();
        if(loggedIn){
            logger.info(String.format("Logged in as: %s", username));
        } else {
            logger.warn(String.format("Failed login attempt with username: %s", username));
        }
    }
}
