package be.rdhaese.packetdelivery.standalone.service.logging.interfaces;

import org.aspectj.lang.JoinPoint;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ContactInformationLogger {
    void afterGet(JoinPoint joinPoint);
    void afterPost(JoinPoint joinPoint);
    void afterGetCompanyName(JoinPoint joinPoint, String companyName);
}
