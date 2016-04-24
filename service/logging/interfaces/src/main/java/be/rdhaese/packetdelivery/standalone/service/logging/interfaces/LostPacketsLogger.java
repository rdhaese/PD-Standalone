package be.rdhaese.packetdelivery.standalone.service.logging.interfaces;

import org.aspectj.lang.JoinPoint;

import java.util.Collection;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
public interface LostPacketsLogger {

    void afterGetLostPackets(JoinPoint joinPoint);

    void afterMarkAsFound(JoinPoint joinPoint);

    void afterRemoveFromSystem(JoinPoint joinPoint);
}
