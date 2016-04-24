package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.ProblematicPacketsLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class ProblematicsPacketsLoggerImpl extends AbstractLogger implements ProblematicPacketsLogger {

    @Autowired
    @Qualifier("problematicPacketsLogger")
    private Logger logger;

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService.getProblematicPackets(..))")
    public void afterGetProblematicPackets(JoinPoint joinPoint) {
        String logText = String.format(
                "Problematic packets requested by [%s]",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService.getProblematicPacket(..))")
    public void afterGetProblematicPacket(JoinPoint joinPoint) {
        String packetId = getArg(joinPoint, 0);
        String logText = String.format(
                "Problematic packet [%s] requested by [%s]",
                packetId,
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService.reSend(..))")
    public void afterReSend(JoinPoint joinPoint) {
        String packetId = getArg(joinPoint, 0);
        String logText = String.format(
                "Problematic packet [%s] status set to normal by [%s]",
                packetId,
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService.returnToSender(..))")
    public void afterReturnToSender(JoinPoint joinPoint) {
        String packetId = getArg(joinPoint, 0);
        String logText = String.format(
                "Problematic packet [%s] returned to sender by [%s]",
                packetId,
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService.getDeliveryAddress(..))")
    public void afterGetDeliveryAddress(JoinPoint joinPoint) {
        String packetId = getArg(joinPoint, 0);
        String logText = String.format(
                "Delivery address of problematic packet [%s] requested by [%s]",
                packetId,
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService.saveDeliveryAddress(..))")
    public void afterSaveDeliveryAddress(JoinPoint joinPoint) {
        DeliveryAddressDTO deliveryAddressDTO = getArg(joinPoint, 0);
        String logText = String.format(
                "Delivery address of problematic packet [%s] saved by [%s]",
                deliveryAddressDTO.getPacketId(),
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
