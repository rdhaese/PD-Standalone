package be.rdhaese.packetdelivery.standalone.service.logging.interfaces;

import org.aspectj.lang.JoinPoint;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AuthenticationLogger {
    void afterAuthenticate(JoinPoint joinPoint, String authenticationResult);
    void beforeLogout(JoinPoint joinPoint);
}
