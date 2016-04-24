package be.rdhaese.packetdelivery.standalone.service.logging.interfaces;

import org.aspectj.lang.JoinPoint;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsLogger {

    void afterGetProblematicPackets(JoinPoint joinPoint);

    void afterGetProblematicPacket(JoinPoint joinPoint);

    void afterReSend(JoinPoint joinPoint);

    void afterReturnToSender(JoinPoint joinPoint);

    void afterGetDeliveryAddress(JoinPoint joinPoint);

    void afterSaveDeliveryAddress(JoinPoint joinPoint);
}
