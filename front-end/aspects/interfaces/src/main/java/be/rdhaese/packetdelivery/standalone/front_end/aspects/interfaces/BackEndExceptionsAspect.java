package be.rdhaese.packetdelivery.standalone.front_end.aspects.interfaces;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author Robin D'Haese
 */
public interface BackEndExceptionsAspect {

    /**
     * Application cannot function without a working connecion with a working backend application.
     * If an exception is thrown when connecting with the backend application, this should be notified to the user. After this, the application should suspend.
     */
    Object showDialogOnExceptionAndExit(ProceedingJoinPoint joinPoint);
}
