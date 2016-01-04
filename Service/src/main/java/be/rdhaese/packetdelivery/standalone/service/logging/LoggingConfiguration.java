package be.rdhaese.packetdelivery.standalone.service.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
public class LoggingConfiguration {

    @Bean(name = "addPacketLoggerBean")
    public Logger addPacketLogger(){
        return LoggerFactory.getLogger(AddPacketLogger.class);
    }

    @Bean(name = "authenticationLoggerBean")
    public Logger authenticationLogger(){
        return LoggerFactory.getLogger(AuthenticationLogger.class);
    }


    @Bean(name = "editContactInformationLoggerBean")
    public Logger editContactInformationLogger(){
        return LoggerFactory.getLogger(EditContactInformationLogger.class);
    }
}
