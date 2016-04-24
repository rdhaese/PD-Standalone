package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.AuthenticationLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
public class AuthenticationLoggerImpl extends AbstractLogger implements AuthenticationLogger {

    @Autowired
    @Qualifier("authenticationLogger")
    private Logger logger;

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.AuthenticationWebService.authenticate(..))",
            returning = "authenticationResult")
    public void afterAuthenticate(JoinPoint joinPoint, String authenticationResult){
        String username = getArg(joinPoint, 0);
        String logText = String.format(
                "Authentication attempt: username [%s]; result: [%s]",
                username,
                authenticationResult
        );
        info(logText);
    }

    @Override
    @Before("execution(* be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service.AuthenticationWebServiceExtended.logout(..))")
    public void beforeLogout(JoinPoint joinPoint) {
        String logText = String.format(
                "[%s] logging out",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
