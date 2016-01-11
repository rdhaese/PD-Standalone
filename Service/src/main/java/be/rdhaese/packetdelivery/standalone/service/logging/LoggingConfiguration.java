package be.rdhaese.packetdelivery.standalone.service.logging;

import be.rdhaese.packetdelivery.standalone.service.logging.impl.AddPacketLoggerImpl;
import be.rdhaese.packetdelivery.standalone.service.logging.impl.AuthenticationLoggerImpl;
import be.rdhaese.packetdelivery.standalone.service.logging.impl.EditContactInformationLoggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
public class LoggingConfiguration {

    @Bean(name = "addPacketLoggerBean")
    public Logger addPacketLogger(){
        return LoggerFactory.getLogger(AddPacketLoggerImpl.class);
    }

    @Bean(name = "authenticationLoggerBean")
    public Logger authenticationLogger(){
        return LoggerFactory.getLogger(AuthenticationLoggerImpl.class);
    }


    @Bean(name = "editContactInformationLoggerBean")
    public Logger editContactInformationLogger(){
        return LoggerFactory.getLogger(EditContactInformationLoggerImpl.class);
    }
}