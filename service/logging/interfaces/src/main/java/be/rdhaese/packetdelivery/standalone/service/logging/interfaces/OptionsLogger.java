package be.rdhaese.packetdelivery.standalone.service.logging.interfaces;

import be.rdhaese.packetdelivery.dto.OptionsDTO;
import org.aspectj.lang.JoinPoint;

/**
 * Created on 23/04/2016.
 *
 * @author Robin D'Haese
 */
public interface OptionsLogger {
    void afterGetFor(JoinPoint joinPoint);
    void afterSave(JoinPoint joinPoint);
}
