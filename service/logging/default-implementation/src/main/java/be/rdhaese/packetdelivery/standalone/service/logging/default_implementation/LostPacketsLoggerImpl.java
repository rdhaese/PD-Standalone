package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.LostPacketsLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class LostPacketsLoggerImpl extends AbstractLogger implements LostPacketsLogger {

    @Autowired
    @Qualifier("lostPacketsLogger")
    private Logger logger;

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService.getLostPackets(..))")
    public void afterGetLostPackets(JoinPoint joinPoint) {
        String logText = String.format(
                "Lost packets requested by [%s]",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService.markAsFound(..))")
    public void afterMarkAsFound(JoinPoint joinPoint) {
        Collection<String> packetIds = getArg(joinPoint, 0);
        for (String packetId : packetIds){
            String logText = String.format(
                    "Packet [%s] marked as lost by [%s]",
                    packetId,
                    getLoggedInUser()
            );
            info(logText);
        }
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService.removeFromSystem(..))")
    public void afterRemoveFromSystem(JoinPoint joinPoint) {
        Collection<String> packetIds = getArg(joinPoint, 0);
        for (String packetId : packetIds){
            String logText = String.format(
                    "Packet [%s] removed from system by [%s]",
                    packetId,
                    getLoggedInUser()
            );
            info(logText);
        }
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
