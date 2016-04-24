package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation;

import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.ContactInformationLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
public class ContactInformationLoggerImpl extends AbstractLogger implements ContactInformationLogger {

    @Autowired
    @Qualifier("contactInformationLogger")
    private Logger logger;

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService.get(..))")
    public void afterGet(JoinPoint joinPoint) {
        String logText = String.format(
                "Contact information requested by [%s]",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService.post(..))")
    public void afterPost(JoinPoint joinPoint) {
        String logText = String.format(
                "Contact information saved by [%s]",
                getLoggedInUser()
        );
        info(logText);
    }

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService.getCompanyName(..))", returning="companyName")
    public void afterGetCompanyName(JoinPoint joinPoint, String companyName) {
        String logText = String.format(
                "Company name [%s] requested",
                companyName
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
