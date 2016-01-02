package be.rdhaese.packetdelivery.standalone.service.logging;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class AddPacketLogger {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddPacketLogger.class);

    @Autowired
    private AuthenticationService authenticationService;

    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.standalone.service.AddPacketService.addPacket(..))", returning = "packetId")
    public void afterAddingPacket(JoinPoint joinPoint, String packetId) {
        String client = ((PacketDTO) joinPoint.getArgs()[0]).getClientName();
        logger.info(
                String.format("Packet added by: %s; For client: %s; With id: %s",
                       authenticationService.getLoggedInUser(),
                        client, packetId
                )
        );
    }
}
