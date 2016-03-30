package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.AddPacketLogger;
import be.rdhaese.packetdelivery.standalone.service.interfaces.AuthenticationWebServiceExtended;
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
public class AddPacketLoggerImpl implements AddPacketLogger {

    @Autowired
    @Qualifier("addPacketLoggerBean")
    private Logger logger;

    @Autowired
    private AuthenticationWebServiceExtended authenticationService;

    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService.addPacket(..))",
            returning = "packetId")
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
