package be.rdhaese.packetdelivery.standalone.service.logging;

import org.aspectj.lang.JoinPoint;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AddPacketLogger {
    void afterAddingPacket(JoinPoint joinPoint, String packetId);
}
